package com.pets.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthServiceClient {
    
    @Value("${auth-service.url}")
    private String authServiceUrl;
    
    @Value("${auth-service.validate-endpoint}")
    private String validateEndpoint;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public boolean validateToken(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<Map> response = restTemplate.exchange(
                authServiceUrl + validateEndpoint,
                HttpMethod.POST,
                entity,
                Map.class
            );
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return Boolean.TRUE.equals(response.getBody().get("valid"));
            }
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getUserEmailFromToken(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<Map> response = restTemplate.exchange(
                authServiceUrl + validateEndpoint,
                HttpMethod.POST,
                entity,
                Map.class
            );
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return (String) response.getBody().get("email");
            }
            
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
