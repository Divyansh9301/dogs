# 🐶 Doggo Marketplace

A full-stack marketplace application for buying and selling dogs, built with modern technologies.

## 🏗️ Architecture

- **Frontend**: React 18 + Vite + Tailwind CSS
- **Admin API**: ASP.NET Core 8
- **Marketplace API**: Spring Boot 3 + Java 21
- **Database**: MySQL 8.4

## 📁 Project Structure

```
doggo-marketplace/
├── docker-compose.yml          # MySQL database setup
├── mysql/
│   └── init.sql               # Database schema
├── frontend/                   # React frontend
│   ├── src/
│   ├── package.json
│   └── tailwind.config.js
├── admin-api/                  # ASP.NET Core admin API
│   ├── Controllers/
│   ├── Program.cs
│   └── appsettings.json
└── marketplace-api/            # Spring Boot marketplace API
    ├── src/main/java/
    ├── src/main/resources/
    └── pom.xml
```

## 🚀 Getting Started

### Prerequisites

- Docker and Docker Compose
- Node.js ≥18
- .NET 8 SDK
- Java 21 (SDKMAN recommended)
- Maven

### 1. Start the Database

```bash
docker compose up -d
```

### 2. Start the Admin API (.NET)

```bash
cd admin-api
dotnet restore
dotnet run
```

The admin API will be available at: http://localhost:5000

### 3. Start the Marketplace API (Java)

```bash
cd marketplace-api
./mvnw spring-boot:run
```

The marketplace API will be available at: http://localhost:8081

### 4. Start the Frontend (React)

```bash
cd frontend
npm install
npm run dev
```

The frontend will be available at: http://localhost:5173

## 🗄️ Database Schema

### Users Table
- `id`: Primary key
- `email`: Unique email address
- `password`: Bcrypt hashed password
- `role`: USER or ADMIN
- `created_at`: Timestamp

### Pets Table
- `id`: Primary key
- `name`: Pet name
- `breed`: Dog breed
- `father_breed`: Father's breed (optional)
- `mother_breed`: Mother's breed (optional)
- `age_months`: Age in months
- `gender`: MALE or FEMALE
- `vaccinated`: Boolean
- `price_cents`: Price in cents
- `seller_id`: Foreign key to users
- `sold_out`: Boolean
- `img_url`: Image URL (optional)
- `created_at`: Timestamp

## 🔧 Development

### Frontend Development
- Built with React 18 and Vite for fast development
- Tailwind CSS for styling
- React Router for navigation
- Zustand for state management
- Axios for API calls

### API Development
- **Admin API**: ASP.NET Core 8 with Entity Framework
- **Marketplace API**: Spring Boot 3 with Spring Data JPA
- Both APIs support CORS for frontend integration
- JWT authentication ready

## 🐳 Docker

The MySQL database runs in Docker for easy development:

```bash
# Start database
docker compose up -d

# Stop database
docker compose down

# View logs
docker compose logs db
```

## 📝 API Endpoints

### Admin API (ASP.NET Core)
- `GET /WeatherForecast` - Sample endpoint
- Swagger UI available at: http://localhost:5000/swagger

### Marketplace API (Spring Boot)
- `GET /api/pets` - List all pets
- `GET /api/pets/{id}` - Get specific pet
- Running on port 8081

## 🎨 Frontend Features

- Modern, responsive design with Tailwind CSS
- Dark mode support
- Mobile-friendly interface
- Real-time updates

## 🔐 Security

- Password hashing with bcrypt
- JWT token authentication
- CORS configuration
- Input validation

## 📦 Dependencies

### Frontend
- React 18.2.0
- React Router DOM 6.0.0
- Axios 1.0.0
- Zustand 4.0.0
- Tailwind CSS 3.0.0

### Admin API
- ASP.NET Core 8
- Entity Framework Core
- Pomelo.EntityFrameworkCore.MySql
- JWT Bearer Authentication

### Marketplace API
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- MySQL Connector

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

---

Happy coding! 🐾 