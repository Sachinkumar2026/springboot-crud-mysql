# 🚀 Spring Boot CRUD API with MySQL

A production-ready REST API built using Spring Boot and MySQL.

## 📌 Features

- ✅ Create, Read, Update, Delete (CRUD) operations
- ✅ DTO pattern implementation
- ✅ Validation using Jakarta Validation
- ✅ Global Exception Handling
- ✅ Pagination and Sorting
- ✅ MySQL database integration
- ✅ Unique email constraint (database-level)
- ✅ Clean layered architecture (Controller → Service → Repository)

---

## 🛠 Tech Stack

- Java 24
- Spring Boot 4
- Spring Data JPA
- Hibernate
- MySQL 8
- Maven

---

## 📂 Project Structure

controller → Handles REST endpoints
service → Business logic
repository → Database access
entity → JPA entities
DTO → Data Transfer Objects
exception → Global exception handling

---

## ⚙️ How to Run

### 1️⃣ Clone the repository

git clone https://github.com/SachinKumar2026/springboot-crud-mysql.git


### 2️⃣ Create MySQL database
CREATE DATABASE crud_db;


### 3️⃣ Configure database credentials

Update `application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/crud_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD


### 4️⃣ Run the application

mvn spring-boot:run

Server runs on: http://localhost:8080


---

## 📬 API Endpoints

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | /api/users | Create user |
| GET | /api/users | Get all users |
| GET | /api/users/{id} | Get user by ID |
| PUT | /api/users/{id} | Update user |
| DELETE | /api/users/{id} | Delete user |

---

## 🧠 Learning Highlights

- Implemented proper exception handling
- Enforced database-level constraints
- Structured layered architecture
- Designed RESTful APIs following best practices

---

## 👨‍💻 Author

Sachin Kumar

SachinKumar2026




