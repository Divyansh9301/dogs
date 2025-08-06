package com.tinyfur.auth.service;

import com.tinyfur.auth.dto.AuthResponse;
import com.tinyfur.auth.dto.LoginRequest;
import com.tinyfur.auth.dto.RegisterRequest;
import com.tinyfur.auth.entity.City;
import com.tinyfur.auth.entity.Role;
import com.tinyfur.auth.entity.User;
import com.tinyfur.auth.repository.CityRepository;
import com.tinyfur.auth.repository.RoleRepository;
import com.tinyfur.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
        
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();
        
        if (!user.getIsActive()) {
            throw new RuntimeException("User account is deactivated");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("role", user.getRole().getRoleName());
        claims.put("username", user.getUsername());

        String token = jwtService.generateToken(user.getEmail(), claims);

        return new AuthResponse(
            token,
            user.getEmail(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole().getRoleName(),
            user.getUserId()
        );
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        // Check if user already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Get city
        Optional<City> cityOpt = cityRepository.findById(registerRequest.getCityId());
        if (cityOpt.isEmpty()) {
            throw new RuntimeException("Invalid city");
        }

        // Get user role (default)
        Optional<Role> roleOpt = roleRepository.findByRoleName("User");
        if (roleOpt.isEmpty()) {
            throw new RuntimeException("Default role not found");
        }

        // Create new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setContactNo(registerRequest.getContactNo());
        user.setAddress(registerRequest.getAddress());
        user.setAadharNo(registerRequest.getAadharNo());
        user.setCity(cityOpt.get());
        user.setRole(roleOpt.get());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setSecurityQuestion(registerRequest.getSecurityQuestion());
        user.setSecurityAnswer(registerRequest.getSecurityAnswer());
        user.setIsActive(true);

        User savedUser = userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getUserId());
        claims.put("role", savedUser.getRole().getRoleName());
        claims.put("username", savedUser.getUsername());

        String token = jwtService.generateToken(savedUser.getEmail(), claims);

        return new AuthResponse(
            token,
            savedUser.getEmail(),
            savedUser.getUsername(),
            savedUser.getFirstName(),
            savedUser.getLastName(),
            savedUser.getRole().getRoleName(),
            savedUser.getUserId()
        );
    }
} 