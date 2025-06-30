# Student Management System - Authentication Testing

This is a Spring Boot application with JWT authentication. Authorization has been removed - any authenticated user can access all endpoints.

## Quick Start

1. **Start the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

2. **Test the auth endpoint (no authentication required):**
   ```bash
   curl http://localhost:3000/api/auth/test
   ```

## Authentication Testing

### 1. Register a new user
```bash
curl -X POST http://localhost:3000/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

### 2. Login to get JWT token
```bash
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

**Response will be:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### 3. Test authenticated endpoints

**Get user profile:**
```bash
curl -X GET http://localhost:3000/api/user/profile \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

**Get admin dashboard:**
```bash
curl -X GET http://localhost:3000/api/admin/dashboard \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

**Get all students:**
```bash
curl -X GET http://localhost:3000/api/students \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

**Create a student:**
```bash
curl -X POST http://localhost:3000/api/students \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE" \
  -d '{
    "name": "John Doe",
    "course": "Computer Science",
    "email": "john@example.com"
  }'
```

## Available Endpoints

### Public (No Authentication Required)
- `GET /api/auth/test` - Test auth endpoint
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login and get JWT token

### Protected (Authentication Required)
- `GET /api/user/profile` - Get user profile
- `GET /api/admin/dashboard` - Admin dashboard
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create new student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student

## Database Configuration
- MySQL database: `student_management`
- Username: `root`
- Password: `dvv@132006`
- Port: `3306`

Make sure MySQL is running and the database exists before starting the application. 