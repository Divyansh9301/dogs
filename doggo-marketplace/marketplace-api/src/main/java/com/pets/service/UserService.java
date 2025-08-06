package com.pets.service;

import com.pets.dto.LoginRequest;
import com.pets.dto.RegisterRequest;
import com.pets.model.User;
import com.pets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    public Map<String, Object> register(RegisterRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmailId(request.getEmailId())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Check if contact number already exists
        if (userRepository.existsByContactNo(request.getContactNo())) {
            throw new RuntimeException("Contact number already exists");
        }
        
        // Check if Aadhar number already exists
        if (userRepository.existsByAadharNo(request.getAadharNo())) {
            throw new RuntimeException("Aadhar number already exists");
        }

        // Create new User entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmailId(request.getEmailId());
        user.setContactNo(request.getContactNo());
        user.setAddress(request.getAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAadharNo(request.getAadharNo());
        user.setCityid(request.getCityid());
        user.setRoleid(request.getRoleid());

        // Save user to database
        User savedUser = userRepository.save(user);
        
        // Generate JWT token
        String token = jwtService.generateToken(savedUser);

        // Create response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", createUserResponse(savedUser));
        
        return response;
    }

    public Map<String, Object> login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmailId(request.getEmailId());
        
        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        User user = userOpt.get();
        String token = jwtService.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", createUserResponse(user));
        
        return response;
    }

    public User getUserById(Long userid) {
        return userRepository.findByUserid(userid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String emailId) {
        return userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Map<String, Object> createUserResponse(User user) {
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("userid", user.getUserid());
        userResponse.put("emailId", user.getEmailId());
        userResponse.put("username", user.getUsername());
        userResponse.put("contactNo", user.getContactNo());
        userResponse.put("address", user.getAddress());
        userResponse.put("cityid", user.getCityid());
        userResponse.put("roleid", user.getRoleid());
        return userResponse;
    }
} 