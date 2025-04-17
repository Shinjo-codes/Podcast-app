# Podcast-app
Technical assessment for Bloocode Technology

 Podcast Platform API — Deployment Guide

This project is a RESTful API for managing podcasts, built with **Spring Boot**, **MySQL**, and containerized using **Docker**. This guide explains how to run, test, and deploy the application locally or inside containers — exactly what you would need to get up to speed and running fast.



 Requirements

Make sure the following are installed:

- Java 17+
- Maven 3.x
- Docker
- Docker Compose



 🏗️ Local Development (No Docker)

This mode is good for development and testing in a local dev environment.



 1. Create a MySQL database

Create a new database using any MySQL client or CLI:

```sql
CREATE DATABASE podcast_db;
```



 2. Configure your datasource

In `src/main/resources/application.yml`, update:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/podcast_db
    username: root
    password: root
```

Adjust credentials as needed.

---

 3. Run the app

```bash
./mvnw spring-boot:run
```

---

 4. Access endpoints

- Swagger UI: `http://localhost:8080/api/documentation`
- Podcast API: `http://localhost:8080/api/podcasts`
- Categories API: `http://localhost:8080/api/categories`
- Episodes API: `http://localhost:8080/api/episodes`

---

 🐳 Docker-Based Setup (Production Simulated)

This setup is for running the app and database inside containers using Docker Compose.

---

 1. Package the app

Build the `.jar` file:

```bash
./mvnw clean package -DskipTests
```

Make sure the JAR is located at:  
`target/podcast-api.jar`

---

 2. Project structure should look like this:

```
.
├── Dockerfile
├── docker-compose.yml
├── .env.example
├── target/
│   └── podcast-api.jar
├── src/
└── ...
```

---

 3. Start containers

```bash
docker-compose up --build
```

What this does:
- Builds your Spring Boot app
- Spins up MySQL with your database
- Binds `localhost:8080` for API
- Injects DB config via environment variables



 4. API Access

Once up:

- Swagger UI: `http://localhost:8080/api/documentation`
- DB: MySQL 8 running on `localhost:3306`  
  Credentials → `root / root`, DB name → `podcast_db`



 5. Stopping containers

```bash
docker-compose down
```



 ⚙️ Config Notes

Environment variables used by Docker:

```env
SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/podcast_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

They are loaded from `docker-compose.yml` and can be moved to a `.env` file for secrets.


 ✅ Quick Start Cheat Sheet

| Task                       | Command                          |
|----------------------------|----------------------------------|
| Build JAR                  | `./mvnw clean package -DskipTests` |
| Run Locally (no Docker)    | `./mvnw spring-boot:run`         |
| Start with Docker Compose  | `docker-compose up --build`      |
| Shutdown containers        | `docker-compose down`            |
| Swagger Docs               | `http://localhost:8080/api/documentation` |

I hope this documentation brings you up to speed with running this application.

Enjoy!
