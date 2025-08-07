# Dogs Marketplace - Complete API Endpoints Documentation

## Overview
This document provides a comprehensive list of all API endpoints in the Dogs Marketplace application, including authentication requirements, port numbers, data formats, and frontend-backend connections.

## Services Architecture

### 🌐 Frontend (React + Vite)
- **Port:** 5173
- **URL:** http://localhost:5173
- **Technology:** React.js with Vite build tool

### 🔐 Auth Service (Java Spring Boot)
- **Port:** 8080
- **URL:** http://localhost:8080
- **Technology:** Java Spring Boot
- **Purpose:** User authentication and location services

### 🏪 Marketplace API (Java Spring Boot)
- **Port:** 8081
- **URL:** http://localhost:8081
- **Technology:** Java Spring Boot
- **Purpose:** Main marketplace operations (pets, orders, users)

### 👑 Admin API (C# .NET Core)
- **Port:** 5000
- **URL:** http://localhost:5000
- **Technology:** C# .NET Core
- **Purpose:** Admin panel operations and management

### 🐕 Pet Service (C# .NET Core)
- **Port:** 5001
- **URL:** http://localhost:5001
- **Technology:** C# .NET Core
- **Purpose:** Pet-specific operations and breed management

### 🗄️ MySQL Database
- **Port:** 3306
- **URL:** localhost:3306
- **Database:** p26__tinyfurdb

---

## 🔒 Authentication Requirements

### Public Endpoints (No Authentication Required)
- User registration and login
- Pet browsing and search
- Basic pet information viewing
- Location services (states/cities)

### User Authentication Required (User Token)
- Creating pet listings
- Updating/deleting own pet listings
- Creating orders
- Viewing own orders
- Profile management

### Admin Authentication Required (Admin Token)
- All admin dashboard operations
- User management
- System-wide pet management
- Order management
- Analytics and statistics

---

## 📋 Complete API Endpoints

## 1. Auth Service (Port 8080)

### 🔐 Authentication Endpoints
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| POST | `/api/auth/login` | ❌ Public | User login | `{email, password}` |
| POST | `/api/auth/register` | ❌ Public | User registration | `{name, email, password}` |

### 📍 Location Endpoints
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| GET | `/api/states` | ❌ Public | Get all states | None |
| GET | `/api/cities` | ❌ Public | Get all cities | None |
| GET | `/api/cities/state/{stateId}` | ❌ Public | Get cities by state | Path: `stateId` |

---

## 2. Marketplace API (Port 8081)

### 🔐 Authentication Endpoints
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| POST | `/api/auth/register` | ❌ Public | User registration | `{name, email, password}` |
| POST | `/api/auth/login` | ❌ Public | User login | `{email, password}` |

### 🐕 Pet Management Endpoints
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| GET | `/api/pets` | ❌ Public | Get all available pets | None |
| GET | `/api/pets/{id}` | ❌ Public | Get pet by ID | Path: `id` |
| GET | `/api/pets/search?q={term}` | ❌ Public | Search pets | Query: `q` |
| GET | `/api/pets/breed/{breed}` | ❌ Public | Get pets by breed | Path: `breed` |
| GET | `/api/pets/seller/{sellerId}` | ❌ Public | Get pets by seller | Path: `sellerId` |
| POST | `/api/pets?sellerId={id}` | 🔒 User Auth | Create pet listing | Query: `sellerId` + Pet object |
| PUT | `/api/pets/{id}?sellerId={sellerId}` | 🔒 User Auth | Update pet listing | Path: `id`, Query: `sellerId` + Pet object |
| DELETE | `/api/pets/{id}?sellerId={sellerId}` | 🔒 User Auth | Delete pet listing | Path: `id`, Query: `sellerId` |

### 🛒 Order Management Endpoints
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| POST | `/api/orders?buyerId={id}` | 🔒 User Auth | Create order | Query: `buyerId` + `{petId, paymentMethod}` |
| GET | `/api/orders/{id}` | 🔒 User Auth | Get order by ID | Path: `id` |
| GET | `/api/orders/buyer/{buyerId}` | 🔒 User Auth | Get buyer's orders | Path: `buyerId` |
| GET | `/api/orders/seller/{sellerId}` | 🔒 User Auth | Get seller's orders | Path: `sellerId` |
| POST | `/api/orders/{orderId}/payment` | 🔒 User Auth | Process payment | Path: `orderId` + `{transactionId}` |
| PUT | `/api/orders/{orderId}/status` | 🔒 User Auth | Update order status | Path: `orderId` + `{status}` |

---

## 3. Admin API (Port 5000)

### 🔐 Admin Authentication
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| POST | `/api/admin/login` | ❌ Public | Admin login | `{email, password}` |

### 👑 Admin Management Endpoints
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| GET | `/api/admin/dashboard/stats` | 🔒 Admin Auth | Get dashboard statistics | None |
| GET | `/api/admin/users` | 🔒 Admin Auth | Get all users | None |
| GET | `/api/admin/pets` | 🔒 Admin Auth | Get all pets | None |
| GET | `/api/admin/orders` | 🔒 Admin Auth | Get all orders | None |
| DELETE | `/api/admin/pets/{id}` | 🔒 Admin Auth | Delete any pet | Path: `id` |
| DELETE | `/api/admin/users/{id}` | 🔒 Admin Auth | Delete user | Path: `id` |

---

## 4. Pet Service (Port 5001)

### 🐕 Pet Operations
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| GET | `/api/Pet` | ❌ Public | Get all pets | None |
| GET | `/api/Pet/{id}` | ❌ Public | Get pet by ID | Path: `id` |
| GET | `/api/Pet/seller/{sellerId}` | ❌ Public | Get pets by seller | Path: `sellerId` |
| GET | `/api/Pet/search?term={term}` | ❌ Public | Search pets | Query: `term` |
| POST | `/api/Pet` | 🔒 User Auth | Create pet | Pet object |
| PUT | `/api/Pet/{id}` | 🔒 User Auth | Update pet | Path: `id` + Pet object |
| DELETE | `/api/Pet/{id}` | 🔒 User Auth | Delete pet | Path: `id` |
| POST | `/api/Pet/book` | 🔒 User Auth | Book pet | Booking object |

### 🏷️ Breed Management
| Method | Endpoint | Auth Required | Purpose | Data Required |
|--------|----------|---------------|---------|---------------|
| GET | `/api/Breed` | ❌ Public | Get all breeds | None |

---

## 🔄 Frontend to Backend Mapping

### Frontend Service Files and Their Backend Connections

#### 1. authService.js
```javascript
// Admin Auth (connects to Admin API - Port 5000)
adminApi.post('/api/admin/login', {email, password})

// User Auth (connects to Marketplace API - Port 8081)
marketplaceApi.post('/api/auth/login', {email, password})
marketplaceApi.post('/api/auth/register', userData)
marketplaceApi.put('/api/auth/profile', userData)
```

#### 2. petService.js (connects to Marketplace API - Port 8081)
```javascript
marketplaceApi.get('/api/pets')
marketplaceApi.get('/api/pets/${id}')
marketplaceApi.post('/api/pets?sellerId=${sellerId}', petData)
marketplaceApi.put('/api/pets/${id}?sellerId=${sellerId}', petData)
marketplaceApi.delete('/api/pets/${id}?sellerId=${sellerId}')
marketplaceApi.get('/api/pets/search?q=${searchTerm}')
marketplaceApi.get('/api/pets/breed/${breed}')
marketplaceApi.get('/api/pets/seller/${sellerId}')
```

#### 3. orderService.js (connects to Marketplace API - Port 8081)
```javascript
marketplaceApi.post('/api/orders?buyerId=${buyerId}', orderData)
marketplaceApi.get('/api/orders/${id}')
marketplaceApi.get('/api/orders/buyer/${buyerId}')
marketplaceApi.get('/api/orders/seller/${sellerId}')
marketplaceApi.post('/api/orders/${orderId}/payment', {transactionId})
marketplaceApi.put('/api/orders/${orderId}/status', {status})
```

#### 4. adminService.js (connects to Admin API - Port 5000)
```javascript
adminApi.get('/api/admin/dashboard/stats')
adminApi.get('/api/admin/users')
adminApi.get('/api/admin/pets')
adminApi.get('/api/admin/orders')
adminApi.delete('/api/admin/pets/${petId}')
adminApi.delete('/api/admin/users/${userId}')
```

---

## 🔐 Authentication Tokens

### User Token
- **Storage:** `localStorage.getItem('user_token')`
- **Header:** `Authorization: Bearer {token}`
- **Scope:** Marketplace API operations
- **Expiration:** 24 hours

### Admin Token
- **Storage:** `localStorage.getItem('admin_token')`
- **Header:** `Authorization: Bearer {token}`
- **Scope:** Admin API operations
- **Expiration:** 24 hours

---

## 📊 Data Structures

### Pet Object
```json
{
  "name": "string (required)",
  "breed": "string (required)",
  "fatherBreed": "string (optional)",
  "motherBreed": "string (optional)",
  "ageMonths": "integer (required, 1-300)",
  "gender": "enum: MALE|FEMALE (required)",
  "vaccinated": "boolean (required)",
  "priceCents": "integer (required, min: 100)",
  "imgUrl": "string (optional)",
  "medicalHistory": "string (optional)",
  "description": "string (optional)",
  "location": "string (optional)"
}
```

### Order Object
```json
{
  "petId": "integer (required)",
  "paymentMethod": "string (required)"
}
```

### User Registration
```json
{
  "name": "string (required, 2-100 chars)",
  "email": "string (required, valid email)",
  "password": "string (required, min 6 chars)"
}
```

### Login Request
```json
{
  "email": "string (required)",
  "password": "string (required)"
}
```

---

## 🚨 CORS Configuration

### Allowed Origins
- Frontend: `http://localhost:5173`
- Methods: `GET, POST, PUT, DELETE, OPTIONS`
- Headers: `*`
- Credentials: `true`

---

## 🔧 Error Handling

### Common Error Responses
```json
{
  "error": "Error message description"
}
```

### HTTP Status Codes
- `200` - Success
- `201` - Created
- `400` - Bad Request
- `401` - Unauthorized
- `403` - Forbidden
- `404` - Not Found
- `500` - Internal Server Error

---

## 🔄 Environment Variables & Configuration

### Frontend (.env)
```
VITE_ADMIN_API_URL=http://localhost:5000
VITE_MARKETPLACE_API_URL=http://localhost:8081
```

### Backend Ports Summary
| Service | Port | Protocol | Purpose |
|---------|------|----------|---------|
| Frontend | 5173 | HTTP | React development server |
| Auth Service | 8080 | HTTP | User authentication |
| Marketplace API | 8081 | HTTP | Main business logic |
| Admin API | 5000 | HTTP | Admin operations |
| Pet Service | 5001 | HTTP | Pet-specific operations |
| MySQL | 3306 | TCP | Database |

---

## 🚀 Getting Started

1. **Start Database:** `docker compose up -d`
2. **Start Auth Service:** `cd auth-service && ./mvnw spring-boot:run`
3. **Start Marketplace API:** `cd marketplace-api && ./mvnw spring-boot:run`
4. **Start Admin API:** `cd admin-api && dotnet run`
5. **Start Pet Service:** `cd pet-service && dotnet run`
6. **Start Frontend:** `cd frontend && npm run dev`

## 📝 Notes

- All endpoints support JSON request/response format
- Authentication tokens are JWT-based
- CORS is enabled for local development
- Database migrations are handled automatically on startup
- Frontend automatically redirects to login on 401 errors 

## 🔧 Complete Implementation Changes

### **Fix 1: Update marketplace-api database configuration**

**File: `/marketplace-api/src/main/resources/application.yaml`**
```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/p26__tinyfurdb  # FIXED: Correct database
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: none  # FIXED: Tables already exist
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
        format_sql: true

# NEW: Auth service communication
auth-service:
  url: http://localhost:8080
  validate-endpoint: /api/auth/validate

logging:
  level:
    com.pets: DEBUG
    org.springframework.security: DEBUG
```

### **Fix 2: Delete Duplicate AuthController**

**DELETE FILE:** `/marketplace-api/src/main/java/com/pets/controller/AuthController.java`

### **Fix 3: Add Token Validation Endpoint to Auth-Service**

**ADD to `/auth-service/src/main/java/com/tinyfur/auth/controller/AuthController.java`:**

```java
// Add this new endpoint to the existing AuthController
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
```

**ADD to `/auth-service/src/main/java/com/tinyfur/auth/service/AuthService.java`:**

```java
// Add this method to the existing AuthService class
public String validateTokenAndGetUser(String token) {
    try {
        String email = jwtService.extractEmail(token);
        if (jwtService.isTokenValid(token, email)) {
            // Optional: Check if user still exists in database
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                return email;
            }
        }
        return null;
    } catch (Exception e) {
        return null;
    }
}
```

### **Fix 4: Create Auth Service Client in marketplace-api**

**CREATE NEW FILE: `/marketplace-api/src/main/java/com/pets/service/AuthServiceClient.java`**

```java
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
```

### **Fix 5: Create JWT Authentication Filter for marketplace-api**

**CREATE NEW FILE: `/marketplace-api/src/main/java/com/pets/security/JwtAuthenticationFilter.java`**

```java
package com.pets.security;

import com.pets.service.AuthServiceClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private AuthServiceClient authServiceClient;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            
            // Validate token with auth-service
            if (authServiceClient.validateToken(jwt)) {
                String userEmail = authServiceClient.getUserEmailFromToken(jwt);
                
                if (userEmail != null) {
                    // Set authentication context
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(userEmail, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
```

### **Fix 6: Update SecurityConfig in marketplace-api**

**UPDATE FILE: `/marketplace-api/src/main/java/com/pets/config/SecurityConfig.java`**

```java
package com.pets.config;

import com.pets.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                // Public endpoints - no authentication required
                .requestMatchers("/api/pets", "/api/pets/{id}", "/api/pets/search", "/api/pets/breed/**").permitAll()
                // All other endpoints require authentication
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

### **Fix 7: Update UserService in marketplace-api - Remove Auth Logic**

**UPDATE FILE: `/marketplace-api/src/main/java/com/pets/service/UserService.java`**

```java
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
```

### **Fix 8: Simple Username/Password Auth for Admin-API**

For admin-api, since you prefer simple username search instead of JWT validation, we can implement basic authentication:

**UPDATE FILE: `/admin-api/Controllers/AdminController.cs`**

```csharp
[ApiController]
[Route("api/admin")]
public class AdminController : ControllerBase
{
    private readonly AdminService _adminService;

    public AdminController(AdminService adminService)
    {
        _adminService = adminService;
    }

    [HttpPost("login")]
    [AllowAnonymous]
    public async Task<IActionResult> Login([FromBody] AdminLoginRequest request)
    {
        try
        {
            // Simple username/password validation
            var admin = await _adminService.ValidateAdminAsync(request.Email, request.Password);
            if (admin == null)
            {
                return BadRequest(new { error = "Invalid credentials" });
            }

            // Return user info without JWT
            return Ok(new { user = admin, authenticated = true });
        }
        catch (Exception ex)
        {
            return BadRequest(new { error = ex.Message });
        }
    }

    // For other endpoints, just check if user exists in database
    [HttpGet("dashboard/stats")]
    public async Task<IActionResult> GetDashboardStats([FromQuery] string username)
    {
        try
        {
            // Simple username validation
            var admin = await _adminService.GetAdminByUsernameAsync(username);
            if (admin == null || admin.RoleId != 1) // Assuming 1 = Admin role
            {
                return Unauthorized(new { error = "Access denied" });
            }

            var stats = await _adminService.GetDashboardStatsAsync();
            return Ok(stats);
        }
        catch (Exception ex)
        {
            return BadRequest(new { error = ex.Message });
        }
    }

    // Apply same pattern to other admin endpoints...
}
```

## 🔄 Updated Service Communication Flow

### **After Implementation:**

```
1. User Authentication:
   Frontend → Auth-Service (8080) → JWT Token

2. Pet Operations (Authenticated):
   Frontend → Marketplace-API (8081) with JWT → 
   Marketplace-API → Auth-Service validate → Business Logic

3. Admin Operations (Simple Auth):
   Frontend → Admin-API (5000) with username/password → 
   Direct database validation → Admin Logic

4. Public Pet Browsing:
   Frontend → Marketplace-API (8081) → Direct access (no auth)
```

## 🎯 Summary of Changes

1. ✅ **Fixed Database**: marketplace-api now uses `p26__tinyfurdb`
2. ✅ **Removed Duplicate Auth**: Deleted AuthController from marketplace-api
3. ✅ **Service Communication**: marketplace-api validates tokens via auth-service
4. ✅ **Admin Simple Auth**: Username-based validation for admin-api
5. ✅ **Public Endpoints**: Pet browsing doesn't require authentication
6. ✅ **Centralized Auth**: Only auth-service handles login/register

**All authentication now flows through auth-service, with proper service-to-service communication for token validation!**

Would you like me to help you implement any specific part of these changes, or do you have any questions about the implementation? 