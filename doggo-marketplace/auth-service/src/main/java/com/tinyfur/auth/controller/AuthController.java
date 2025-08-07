package com.tinyfur.auth.controller;

import com.tinyfur.auth.dto.AuthResponse;
import com.tinyfur.auth.dto.LoginRequest;
import com.tinyfur.auth.dto.RegisterRequest;
import com.tinyfur.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Login failed", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            AuthResponse response = authService.register(registerRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Registration failed", e.getMessage()));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Invalid token", "Authorization header missing or invalid"));
            }
            
            String token = authHeader.substring(7);
            String email = authService.validateTokenAndGetUser(token);
            
            if (email != null) {
                return ResponseEntity.ok(Map.of("valid", true, "email", email));
            } else {
                return ResponseEntity.badRequest().body(new ErrorResponse("Invalid token", "Token is expired or invalid"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Token validation failed", e.getMessage()));
        }
    }

    public static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}
