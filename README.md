# XSWareDBService

**XSWareDBService** is a backend service written in **Kotlin** using **Spring Boot**.  
It is responsible for **data access and persistence**, providing a secure API for retrieving
and storing data in a **MySQL** database.

The service acts as a **database access layer** for the XSWare ecosystem and is primarily
consumed by **XSWareAPI**, which exposes data to the frontend application.


## 🏗️ Architecture Overview

![apps_architecture](https://xsware.pl/assets/img/other/apps_architecture_2.png)


## ✨ Key Features

- Java 17+ with **Spring Boot**
- **Spring Security** with **JWT authentication**
- Stateless REST API
- Secure communication between frontend and database service
- Environment-based configuration (`dev`, `prod`)
- Built with **Gradle**
- Ready for Docker / CI/CD environments

## ✨ Key Features

- Kotlin-based **Spring Boot** application
- **Spring Security** for request authentication and authorization
- **Hibernate + JPA** for ORM and database access
- **MySQL** database integration
- Stateless REST API
- Clear separation of persistence and business logic
- Unit and integration testing with **JUnit 5**
- Gradle-based build system


## 🛠️ Technology Stack

| Layer        | Technology |
|-------------|------------|
| Language    | Kotlin |
| Framework   | Spring Boot |
| Security    | Spring Security |
| ORM         | Hibernate |
| Persistence| JPA |
| Database    | MySQL |
| Connector   | MySQL Connector/J |
| Testing     | JUnit 5 |
| Build Tool  | Gradle |


## ⚙️ Configuration

The application is configured using **Spring profiles** and **environment variables**.  
Sensitive data such as database credentials **must not be committed** to the repository.

### Spring Profiles
- `dev` – development environment
- `prod` – production environment


### Required Environment Variables

```bash
DB_DATASOURCE_URL=<db_url>
DB_DATASOURCE_USERNAME=username
DB_DATASOURCE_PASSWORD=password
```

## 🚀 Running the Application

Local development
```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

Build JAR
```bash
./gradlew clean build
```

Run JAR
```bash
java -jar build/libs/xsware-db-service.jar \
  --spring.profiles.active=prod
```

## 🔐 Security

- Protected endpoints using Spring Security
- Intended to be accessed only by trusted services (e.g. XSWareAPI)
- Stateless request handling
- Authentication mechanism configurable per environment

## 🗄️ Persistence Layer

- Entities mapped using JPA annotations
- Database schema managed via Hibernate
- Repositories implemented using Spring Data JPA
- Transaction management handled by Spring

## 📦 Project Structure

```
src/main/java
  └── pl.xsware
    ├── api
    ├── config
    ├── domain
    └── util

src/main/resources
  ├── application.yml
  ├── application-dev.yml
  └── application-prod.yml
```

## 🧪 Testing

Run tests using:
```bash
./gradlew test
```

## 🧑‍💻 Related Projects

- XSWareAPI – Java Spring Boot API gateway
- XSWareWeb – React frontend (Saldo Planner)