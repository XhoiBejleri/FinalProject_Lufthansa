# Job Portal Backend

This is a Spring Boot-based backend system for a job portal. The project demonstrates a basic implementation of role-based access control, CRUD operations, DTO mapping with MapStruct, and validation using Jakarta Bean Validation. It is intended as a demo/internship project and is not production-ready.

## Overview

The system supports three types of users:
- **Admin:** Can view all users (with pagination and filtering by role) and delete users.
- **Employer:** Can post jobs, view jobs they have posted, view applications for their jobs, update application statuses, and add reviews for jobs.
- **Job Seeker:** Can view available jobs (with pagination and filtering), apply for jobs, upload resumes, and view their applications.

## Technologies Used

- **Spring Boot 3**
- **Spring Data JPA & Hibernate**
- **MySQL**
- **Spring Security** (with in-memory authentication for demo purposes)
- **MapStruct** for mapping between DTOs and entities
- **Jakarta Bean Validation** (JSR-380) for input validation
- **Postman** for API testing

## Features

- **Role-Based Access Control:** Endpoints are secured based on user roles.
- **DTOs & Mappers:** Data Transfer Objects (DTOs) separate the API layer from the persistence layer. MapStruct is used to handle mappings.
- **Validation:** Request payloads are validated using annotations like `@NotBlank`, `@Size`, and `@NotNull`.
- **Global Exception Handling:** A `@ControllerAdvice` class handles validation errors and runtime exceptions, returning user-friendly error messages.
- **Pagination & Filtering:** Endpoints support pagination and filtering of resources.

## Setup Instructions

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/XhoiBejleri/JobPortal_Spring_FinalProject.git
   cd JobPortal_Spring_FinalProject
