package com.pets.service;

import com.pets.model.User;
import com.pets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    // REMOVED: register() and login() methods - auth-service handles these

    public User getUserById(Long userid) {
        return userRepository.findByUserid(userid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String emailId) {
        return userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Map<String, Object> createUserResponse(User user) {
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
