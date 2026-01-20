# F1 Family Fantasy Backend

## Overview
This is the backend for the F1 Family Fantasy platform, built with **Spring Boot 3.4** and **Java 21**. It handles user authentication, league management, and race scoring using the F1 API.

## Tech Stack
- **Java**: 21 (Required)
- **Framework**: Spring Boot 3.4.0
- **Security**: Spring Security 6 (Stateless JWT)
- **Database**: H2 (In-memory for dev), JPA
- **Build Tool**: Maven

## Prerequisites
- **Java 21**: Ensure `JAVA_HOME` is set to a JDK 21 installation.
  - You can check with `java -version`.
  - If using SDKMAN: `sdk use java 21.0.2-open`

## Getting Started

### 1. Build the Project
```bash
mvn clean package
```

### 2. Run the Application
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`.

### 3. Access Database
The H2 Console is enabled for development:
- **URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:f1fantasy`
- **Username**: `sa`
- **Password**: (leave empty)

## API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate and get JWT |

**Public Endpoints**:
- `/api/auth/**`
- `/h2-console/**`

All other endpoints require a `Authorization: Bearer <token>` header.

## Configuration
Configuration is located in `src/main/resources/application.properties`.
- **JWT Secret**: Configured to a 256-bit Hex string.
- **Expiration**: 24 hours.
