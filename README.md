# Employee Management System

A RESTful Employee Management System built using **Java, Spring Boot, Spring Data JPA, and MySQL**. This application provides APIs to manage employee records, including creating, retrieving, updating, deleting, searching, sorting, and paginating employee data.

## Features

- **CRUD Operations:** Create, retrieve, update, and delete employee records.
- **RESTful APIs:** Well-structured REST endpoints following HTTP standards.
- **Input Validation:** Validates employee data using Jakarta Bean Validation.
- **Exception Handling:** Centralized exception handling for invalid requests, missing employees, and duplicate email addresses.
- **Search and Filtering:** Search employees by name, department, and salary range.
- **Pagination and Sorting:** Retrieve employee records in pages and sort them by selected fields.
- **DTO Pattern:** Uses request and response DTOs to separate API data from database entities.
- **JPA and Hibernate:** Uses Spring Data JPA for database operations.
- **Swagger UI:** Interactive API documentation for testing endpoints.
- **Unit Testing:** Tests service and controller layers using JUnit and Mockito.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot | Backend application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database operations |
| Hibernate | ORM |
| MySQL | Relational database |
| Maven | Dependency management and build |
| JUnit 5 | Unit testing |
| Mockito | Mocking dependencies during testing |
| Swagger / OpenAPI | API documentation |
| Git & GitHub | Version control |

## Project Structure

```text
Employee-Management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/Project2/Employee/Management/
│   │   │       ├── Controller/
│   │   │       ├── Entity/
│   │   │       ├── Exception/
│   │   │       ├── Repository/
│   │   │       ├── Service/
│   │   │       ├── config/
│   │   │       ├── dto/
│   │   │       ├── mapper/
│   │   │       ├── response/
│   │   │       ├── specification/
│   │   │       └── EmployeeManagementApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/Project2/Employee/Management/
│               ├── Controller/
│               ├── Service/
│               └── EmployeeManagementApplicationTests.java
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## API Endpoints

Base URL: `http://localhost:8080/api/employees`

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees` | Create a new employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get an employee by ID |
| PUT | `/api/employees/{id}` | Update employee details |
| DELETE | `/api/employees/{id}` | Delete an employee |
| GET | `/api/employees/search` | Search and filter employees |

### Search, Pagination, and Sorting

The search endpoint supports filtering employees by name, department, and salary range. Pagination and sorting can also be used.

Example:

```http
GET /api/employees/search?name=john&department=IT&page=0&size=10
```

Example with salary range:

```http
GET /api/employees/search?minSalary=30000&maxSalary=80000&page=0&size=10
```

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 21
- Maven (or use the included Maven Wrapper)
- MySQL
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/atahar77/employee-management-system.git
```

### 2. Navigate to the Project

```bash
cd employee-management-system
```

### 3. Configure the Database

Create a MySQL database:

```sql
CREATE DATABASE employee_management;
```

Configure your database connection in `src/main/resources/application.properties`.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Set the `DB_USERNAME` and `DB_PASSWORD` environment variables to your MySQL credentials before running the application.

### 4. Run the Application

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start at:

`http://localhost:8080`

### 5. Access Swagger UI

Open the following URL in your browser to explore and test the APIs:

http://localhost:8080/swagger-ui/index.html

## Running Tests

Run the automated tests using the Maven Wrapper:

```bash
./mvnw test
```

The tests cover the service and controller layers, including employee creation, retrieval, validation, and exception scenarios.

## Key Concepts Implemented

- Layered architecture (Controller, Service, Repository)
- Dependency injection and inversion of control
- REST API design
- DTOs and entity mapping
- Spring Data JPA and Hibernate
- Dynamic queries using JPA Specifications
- Pagination and sorting
- Global exception handling
- Unit testing with JUnit and Mockito

## Future Improvements

- Spring Security and role-based authorization
- JWT-based authentication
- Docker containerization
- CI/CD pipeline integration
- Integration testing with Testcontainers

## Author

**Atahar Hajee**

- GitHub: [atahar77](https://github.com/atahar77)
- Project: [Employee Management System](https://github.com/atahar77/employee-management-system)
