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