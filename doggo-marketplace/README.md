# 🐕 Doggo Marketplace

A full-stack pet marketplace application with separate user and admin authentication systems.

## 🏗️ Architecture

- **Frontend**: React 18 + Vite + Tailwind CSS
- **User API**: Java 21 + Spring Boot 3 (Port 8081)
- **Admin API**: .NET Core 8 (Port 5000)
- **Database**: MySQL 8.4 (Docker)

## 🚀 Quick Start

### 1. Start the Database

```bash
# Start MySQL container
docker compose up -d

# Verify database is running
docker ps
```

### 2. Start the Backends

#### Java Backend (User Operations)
```bash
cd marketplace-api

# Install dependencies (if using Maven wrapper)
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The Java backend will be available at: `http://localhost:8081`

#### .NET Backend (Admin Operations)
```bash
cd admin-api

# Restore packages
dotnet restore

# Run the application
dotnet run
```

The .NET backend will be available at: `http://localhost:5000`

### 3. Start the Frontend

```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev
```

The frontend will be available at: `http://localhost:5173`

## 🔐 Authentication

### User Login
- **Email**: `user@example.com`
- **Password**: `password123`
- **API**: `http://localhost:8081/api/auth/login`

### Admin Login
- **Email**: `admin@example.com`
- **Password**: `password123`
- **API**: `http://localhost:5000/api/admin/login`

## 📋 API Endpoints

### Java Backend (User Operations) - Port 8081

#### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login

#### Pets
- `GET /api/pets` - Get all available pets
- `GET /api/pets/{id}` - Get pet by ID
- `POST /api/pets` - Create new pet listing
- `PUT /api/pets/{id}` - Update pet listing
- `DELETE /api/pets/{id}` - Delete pet listing
- `GET /api/pets/search?q={term}` - Search pets
- `GET /api/pets/breed/{breed}` - Get pets by breed
- `GET /api/pets/seller/{sellerId}` - Get pets by seller

#### Orders
- `POST /api/orders` - Create order (buy pet)
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders/buyer/{buyerId}` - Get orders by buyer
- `GET /api/orders/seller/{sellerId}` - Get orders by seller
- `POST /api/orders/{orderId}/payment` - Process payment
- `PUT /api/orders/{orderId}/status` - Update order status

### .NET Backend (Admin Operations) - Port 5000

#### Authentication
- `POST /api/admin/login` - Admin login

#### Dashboard
- `GET /api/admin/dashboard/stats` - Get dashboard statistics

#### Management
- `GET /api/admin/users` - Get all users
- `GET /api/admin/pets` - Get all pets
- `GET /api/admin/orders` - Get all orders
- `DELETE /api/admin/pets/{id}` - Delete pet (admin only)
- `DELETE /api/admin/users/{id}` - Delete user (admin only)

## 🗄️ Database Schema

### Users Table
```sql
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(128) UNIQUE NOT NULL,
  password CHAR(60) NOT NULL,
  name VARCHAR(100) NOT NULL,
  role ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Pets Table
```sql
CREATE TABLE pets (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  breed VARCHAR(64) NOT NULL,
  father_breed VARCHAR(64),
  mother_breed VARCHAR(64),
  age_months INT NOT NULL,
  gender ENUM('MALE','FEMALE') NOT NULL,
  vaccinated BOOLEAN NOT NULL,
  price_cents INT NOT NULL,
  seller_id BIGINT,
  sold_out BOOLEAN DEFAULT FALSE,
  img_url VARCHAR(256),
  medical_history TEXT,
  description TEXT,
  location VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (seller_id) REFERENCES users(id)
);
```

### Orders Table
```sql
CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  buyer_id BIGINT NOT NULL,
  pet_id BIGINT NOT NULL,
  total_amount_cents INT NOT NULL,
  processing_fee_cents INT NOT NULL DEFAULT 5000,
  status ENUM('PENDING','PAID','COMPLETED','CANCELLED') NOT NULL DEFAULT 'PENDING',
  payment_method VARCHAR(50),
  transaction_id VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (buyer_id) REFERENCES users(id),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
);
```

## 🔧 Features

### User Features
- ✅ User registration and login
- ✅ Browse available pets
- ✅ Search and filter pets
- ✅ View pet details
- ✅ Post pets for sale
- ✅ Buy pets with payment processing
- ✅ View order history
- ✅ Profile management

### Admin Features
- ✅ Admin authentication
- ✅ Dashboard with statistics
- ✅ View all users, pets, and orders
- ✅ Delete inappropriate posts
- ✅ Manage user accounts
- ✅ Monitor marketplace activity

### Payment System
- ✅ Order creation with processing fees
- ✅ Payment method selection
- ✅ Transaction tracking
- ✅ Order status management

## 🛠️ Development

### Prerequisites
- Node.js 18+
- Java 21
- .NET 8 SDK
- Docker & Docker Compose
- MySQL 8.4

### Environment Setup
1. **Database**: MySQL runs on port 3306
2. **Java API**: Spring Boot runs on port 8081
3. **.NET API**: ASP.NET Core runs on port 5000
4. **Frontend**: Vite dev server runs on port 5173

### Testing the APIs

#### Test User Login
```bash
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password123"}'
```

#### Test Admin Login
```bash
curl -X POST http://localhost:5000/api/admin/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@example.com","password":"password123"}'
```

#### Test Pet Listing
```bash
curl -X GET http://localhost:8081/api/pets
```

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure Docker is running
   - Check if MySQL container is up: `docker ps`
   - Verify connection string in application files

2. **Port Already in Use**
   - Check if ports 5000, 8081, 5173 are available
   - Kill existing processes or change ports in configuration

3. **Frontend Build Errors**
   - Clear node_modules: `rm -rf node_modules && npm install`
   - Check Node.js version: `node --version`

4. **Backend Build Errors**
   - Java: Ensure Java 21 is installed
   - .NET: Ensure .NET 8 SDK is installed
   - Check Maven/NuGet package versions

### Logs
- **Java Backend**: Check console output for Spring Boot logs
- **.NET Backend**: Check console output for ASP.NET Core logs
- **Frontend**: Check browser console and Vite dev server output

## 📝 License

This project is for educational purposes. Feel free to use and modify as needed. 