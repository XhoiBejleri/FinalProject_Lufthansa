# Job Portal Backend

This Spring Boot-based backend system for a job portal demonstrates a RESTful API with role-based access control, JWT authentication, DTO mapping with MapStruct, and validation using Jakarta Bean Validation. This project is intended as a demo/internship project and is not production-ready.

## Overview

The system supports three types of users:

- **Admin:**
   - Can view and delete users.

- **Employer:**
   - Can post jobs, view jobs they have posted, view applications for their jobs, update application statuses, and add reviews for jobs.

- **Job Seeker:**
   - Can view available jobs, apply for jobs, and view their own applications.

## Technologies Used

- **Spring Boot 3**
- **Spring Data JPA & Hibernate**
- **MySQL**
- **Spring Security** with JWT-based authentication
- **MapStruct** for mapping between DTOs and entities
- **Jakarta Bean Validation** (JSR-380) for input validation
- **Postman** for API testing

## Features

- **Resource-Oriented Endpoints:**  
  The API is organized by resource (Jobs, Applications, Reviews, and Users) rather than strictly by user role.

- **JWT Authentication:**
   - A dedicated login endpoint (`/auth/login`) accepts credentials and returns a JWT token.
   - All secured endpoints require the token in the `Authorization` header as a Bearer token.

- **Role-Based Access Control:**  
  Endpoints are secured using Spring Security:
   - Only Admins can access user management endpoints.
   - Only Employers can create or update jobs and add reviews.
   - Applications endpoints are accessible to both Employers and Job Seekers (depending on the use case).

- **DTOs & Mappers:**  
  Data Transfer Objects (DTOs) decouple the API layer from the persistence layer, with MapStruct handling mappings.

- **Validation:**  
  Request payloads are validated using annotations like `@NotBlank`, `@Size`, and `@NotNull`.

- **Global Exception Handling:**  
  A `@ControllerAdvice` class handles validation errors and runtime exceptions, returning user-friendly error messages.

## Setup Instructions

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/JobPortal_Spring_FinalProject.git
   cd JobPortal_Spring_FinalProject