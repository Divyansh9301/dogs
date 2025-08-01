# API Endpoint Testing Guide

## Prerequisites
1. Start MySQL: `docker compose up -d`
2. Start Java Backend: `cd marketplace-api && ./mvnw spring-boot:run`
3. Start .NET Backend: `cd admin-api && dotnet run`
4. Start Frontend: `cd frontend && npm run dev`

## Test Credentials
- **User**: `user@example.com` / `password123`
- **Admin**: `admin@example.com` / `password123`

## Java Backend Tests (Port 8081)

### 1. User Authentication
```bash
# User Login
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password123"}'

# User Registration
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","email":"test@example.com","password":"password123"}'
```

### 2. Pet Operations
```bash
# Get all pets
curl -X GET http://localhost:8081/api/pets

# Create pet (requires sellerId parameter)
curl -X POST "http://localhost:8081/api/pets?sellerId=1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Buddy",
    "breed": "Golden Retriever",
    "fatherBreed": "Golden Retriever",
    "motherBreed": "Golden Retriever",
    "ageMonths": 12,
    "gender": "MALE",
    "vaccinated": true,
    "priceCents": 150000,
    "imgUrl": "https://example.com/buddy.jpg",
    "medicalHistory": "All vaccinations up to date",
    "description": "Friendly and well-trained",
    "location": "New York, NY"
  }'

# Search pets
curl -X GET "http://localhost:8081/api/pets/search?q=Golden"

# Get pets by breed
curl -X GET http://localhost:8081/api/pets/breed/Golden%20Retriever
```

### 3. Order Operations
```bash
# Create order (buy pet)
curl -X POST "http://localhost:8081/api/orders?buyerId=1" \
  -H "Content-Type: application/json" \
  -d '{
    "petId": 1,
    "paymentMethod": "credit_card"
  }'

# Get order by ID
curl -X GET http://localhost:8081/api/orders/1

# Process payment
curl -X POST http://localhost:8081/api/orders/1/payment \
  -H "Content-Type: application/json" \
  -d '{"transactionId":"txn_123456"}'
```

## .NET Backend Tests (Port 5000)

### 1. Admin Authentication
```bash
# Admin Login
curl -X POST http://localhost:5000/api/admin/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@example.com","password":"password123"}'
```

### 2. Admin Operations (requires admin token)
```bash
# Get dashboard stats
curl -X GET http://localhost:5000/api/admin/dashboard/stats \
  -H "Authorization: Bearer YOUR_ADMIN_TOKEN"

# Get all users
curl -X GET http://localhost:5000/api/admin/users \
  -H "Authorization: Bearer YOUR_ADMIN_TOKEN"

# Get all pets
curl -X GET http://localhost:5000/api/admin/pets \
  -H "Authorization: Bearer YOUR_ADMIN_TOKEN"

# Get all orders
curl -X GET http://localhost:5000/api/admin/orders \
  -H "Authorization: Bearer YOUR_ADMIN_TOKEN"

# Delete pet (admin only)
curl -X DELETE http://localhost:5000/api/admin/pets/1 \
  -H "Authorization: Bearer YOUR_ADMIN_TOKEN"

# Delete user (admin only)
curl -X DELETE http://localhost:5000/api/admin/users/1 \
  -H "Authorization: Bearer YOUR_ADMIN_TOKEN"
```

## Frontend Integration Tests

### 1. User Login Flow
1. Navigate to `http://localhost:5173/login`
2. Select "User Login"
3. Enter: `user@example.com` / `password123`
4. Should redirect to home page

### 2. Admin Login Flow
1. Navigate to `http://localhost:5173/login`
2. Select "Admin Login"
3. Enter: `admin@example.com` / `password123`
4. Should redirect to admin dashboard

### 3. Pet Posting Flow
1. Login as user
2. Navigate to "Sell Pet"
3. Fill in all required fields
4. Submit form
5. Verify pet appears in listings

### 4. Pet Buying Flow
1. Browse available pets
2. Click on a pet to view details
3. Click "Book Now"
4. Complete payment process
5. Verify order is created

## Common Issues and Solutions

### 1. Database Connection Issues
- Ensure MySQL container is running: `docker ps`
- Check database logs: `docker compose logs db`
- Verify connection strings in both backends

### 2. Port Conflicts
- Java backend: Port 8081
- .NET backend: Port 5000
- Frontend: Port 5173
- MySQL: Port 3306

### 3. CORS Issues
- Both backends are configured for CORS
- Frontend origin: `http://localhost:5173`

### 4. Authentication Issues
- Check JWT token expiration (24 hours)
- Verify token format in Authorization header
- Clear localStorage if tokens are corrupted

### 5. Missing Dependencies
- Java: Ensure Maven dependencies are downloaded
- .NET: Run `dotnet restore`
- Frontend: Run `npm install`

## Expected Responses

### Successful User Login
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "email": "user@example.com",
    "name": "Demo User",
    "role": "USER"
  }
}
```

### Successful Admin Login
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 2,
    "email": "admin@example.com",
    "name": "Demo Admin",
    "role": "ADMIN"
  }
}
```

### Pet Object Structure
```json
{
  "id": 1,
  "name": "Buddy",
  "breed": "Golden Retriever",
  "fatherBreed": "Golden Retriever",
  "motherBreed": "Golden Retriever",
  "ageMonths": 12,
  "gender": "MALE",
  "vaccinated": true,
  "priceCents": 150000,
  "sellerId": 1,
  "soldOut": false,
  "imgUrl": "https://example.com/buddy.jpg",
  "medicalHistory": "All vaccinations up to date",
  "description": "Friendly and well-trained",
  "location": "New York, NY",
  "createdAt": "2024-01-01T00:00:00"
}
```

### Order Object Structure
```json
{
  "id": 1,
  "buyerId": 1,
  "petId": 1,
  "totalAmountCents": 155000,
  "processingFeeCents": 5000,
  "status": "PENDING",
  "paymentMethod": "credit_card",
  "transactionId": null,
  "createdAt": "2024-01-01T00:00:00",
  "updatedAt": "2024-01-01T00:00:00"
}
``` 