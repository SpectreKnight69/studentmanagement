# 🎓 Student Management System (Spring Boot + JWT + RBAC)

This is a full-stack ready **Student Management System backend** built using **Spring Boot**. It features secure authentication and role-based access control (RBAC) using **JWT tokens**, allowing different access privileges for **Admins**, **Professors**, and **Students**.

---

## 🚀 Features Implemented

### ✅ Authentication
- User Registration with `username`, `password`
- Secure password storage with **BCrypt hashing**
- User Login using **JWT tokens**
- Token is returned on login and must be sent in subsequent requests

### ✅ Role-Based Authorization
- Role stored as `String` in `User` entity (e.g., `ROLE_ADMIN`, `ROLE_PROFESSOR`, `ROLE_STUDENT`)
- Role access verified using `@PreAuthorize` annotations
- Roles currently supported:
  - `ROLE_ADMIN`
  - `ROLE_PROFESSOR`
  - `ROLE_STUDENT`

### ✅ User Roles
- **Admin**: Full access to all student data (CRUD), can view all students and professors.
- **Professor**: Can perform full CRUD operations on students, view their own data. Cannot delete/update professors.
- **Student**: Can only view **their own student data**. Cannot access others’ data or perform any write operations.

### ✅ Student API
- `GET /api/students` – List all students (Admin & Professor)
- `GET /api/students/{id}` – View student by ID (All roles)
- `POST /api/students` – Create a new student (Admin & Professor)
- `PUT /api/students/{id}` – Update a student (Admin only)
- `DELETE /api/students/{id}` – Delete a student (Admin only)

### ✅ JWT Security
- Stateless authentication using **JSON Web Tokens**
- Secure filter that verifies token in every request
- Unauthorized or invalid tokens return `403 Forbidden`

---

## 📦 Tech Stack

| Layer         | Technology        |
|--------------|-------------------|
| Language      | Java 17+          |
| Framework     | Spring Boot       |
| Security      | Spring Security   |
| JWT Library   | io.jsonwebtoken   |
| Database      | MySQL             |
| ORM           | Spring Data JPA   |
| Build Tool    | Maven             |
| Validation    | Jakarta Validation (Bean Validation API) |

---

## 📄 Entities

### 🧑 User
| Field     | Type    | Description               |
|-----------|---------|---------------------------|
| id        | Long    | Auto-generated primary key |
| username  | String  | Unique user identifier    |
| password  | String  | BCrypt-hashed password     |
| role      | String  | User role (`ROLE_...`)     |

### 🎓 Student
| Field     | Type    | Description              |
|-----------|---------|--------------------------|
| id        | Long    | Auto-generated ID         |
| name      | String  | Full name of the student  |
| course    | String  | Enrolled course           |
| email     | String  | Email address             |

---

## 🔐 Security Workflow

1. **Register** via `/api/auth/register`  
2. **Login** via `/api/auth/login`  
   - Use returned JWT token in header for all protected routes:
     ```
     Authorization: Bearer <your-token-here>
     ```
3. Access role-protected APIs (Students, Professors, etc.)

---

## 🧪 Testing With Postman

1. Register users with desired roles manually (or update role in DB)
2. Login to receive JWT token
3. Use token to test each protected route based on user role
