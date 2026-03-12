# Sustainable Island Backend - Production Manual

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![MySQL](https://img.shields.io/badge/MySQL-9.4-orange)
![JWT](https://img.shields.io/badge/JWT-Auth-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📋 Index
- [Overview](#-overview)
- [Development Process & Technology Stack](#-development-process--technology-stack)
- [Configuration Files](#-configuration-files-involved)
- [Installation Process (Prerequisites)](#-installation-process-prerequisites)
- [Deployment & Execution](#-deployment--execution-instructions)

## 📋 Overview
This document details the production environment setup, general architecture, installation processes, and deployment instructions for the Sustainable Island - Las Palmas backend project.

## 🚀 Development Process & Technology Stack
During the backend development, a robust and secure API was designed using the following key technologies:

- **Core Framework**: Java 17 with Spring Boot 3.4.3.
- **Security**: Implemented using Spring Security and JWT (JSON Web Tokens) for restricted access and stateless user sessions. Includes IP filtering and API Rate Limiting (using Bucket4j) against abuse.
- **Database Persistence**: MySQL, mapped using Spring Data JPA (Hibernate).
- **Database Migrations**: handled by Flyway, ensuring all environments (including production) have synchronized schemas.
- **Web Server / Reverse Proxy**: Nginx handles incoming traffic on standard ports (80/443), manages SSL/TLS encryption, and forwards traffic to the internal Spring Boot application.

### Request Flow in Production
`External Client (HTTPS)` → `Nginx (Reverse Proxy + SSL on 443)` → `Spring Boot (port 8080)` → `MySQL Database (port 3307)`

## 🔧 Configuration Files Involved
The production environment relies heavily on three main repository files:

### 1. `application-prod.properties` (Spring Boot Production Config)
- **Database**: Connects to `jdbc:mysql://localhost:3307/seazen` using the `prod` profile.
- **Hibernate & Flyway**: Auto DDL is limited to `validate` mode. Database migrations are handled automatically by Flyway, keeping `spring.flyway.clean-disabled=true` on to avoid accidental data drops.
- **CORS**: Only accepts cross-origin requests originating directly from the assigned frontend domain.
- **Logs**: Re-routed to the server log directory (`/var/log/seazen/application.log`) with a 30-day rotation history policy.

### 2. `nginx-sustainable-island.conf` (Nginx Configuration)
- Listens to HTTP port `80` to force `301` redirects to HTTPS over port `443`.
- Requires SSL certificate paths (`fullchain.pem` and `privkey.pem`) for secure encryption (TLS v1.2 and v1.3).
- *Reverse Proxy Config*: Redicts everything acting on `/` to the local Spring Boot instance `http://127.0.0.1:8080/`, appending essential network headers (`X-Real-IP`, `X-Forwarded-For`) so the backend reads the original client IP and not Nginx's proxy IP. Timeout limits are expanded gracefully to prevent premature `504 Gateway Timeout` exceptions.

### 3. `pom.xml` (Dependencies)
- Manages project dependencies including Core Web Starter, Spring Security, DB connectors (`mysql-connector-j`, `flyway-core`, etc.), JSON Web Token logic (`jjwt-api`), and Rate Limiting helpers (`bucket4j-core`).

## 🛠 Instalation Process (Prerequisites)
Before spinning up the application on the production server, these requirements must be met:

1. **Install Java 17:** Host server needs the Java 17 execution environment installed.
2. **Database (MySQL):**
   - An active instance of MySQL on the host server.
   - You can spin it up isolated via Docker using scripts like `docker-start-containter-mysql.bat` (maps port 3307).
   - Create the initial database:
     ```sql
     CREATE DATABASE seazen;
     ```
   - Make sure your database grants access to user `root` with password `sasa1234` as detailed in `application-prod.properties` (this can be swapped with environment variables for a mature setup).
3. **Log Directory Pipeline:**
   - The `/var/log/seazen/` directory must exist and offer R/W access to the application owner.
     ```bash
     sudo mkdir -p /var/log/seazen
     sudo chown -R $USER:$USER /var/log/seazen
     ```
4. **Web Server (Nginx):**
   - Install Nginx.
   - Copy `nginx-sustainable-island.conf` into your standard nginx configuration tree (like `/etc/nginx/sites-available/` + symlinked to `sites-enabled/`).
   - Validate `.pem` certificates exist at the defined locations.
   - `sudo systemctl reload nginx` (or your service equivalent).

## 🚀 Deployment & Execution Instructions
To initialize the backend context safely within production:

### 1. Compile and Package (JAR building)
From the project root (`Sustainable-Island---Las-Palmas/backend/`), package the application using Maven. Ensure automated tests are skipped if testing was done via CI/CD.

```bash
# Clean project and compile delivery artifact skipping tests
./mvnw clean package -DskipTests
```
After a `BUILD SUCCESS`, an executable bundle `backend-0.0.1-SNAPSHOT.jar` will generate inside the `target/` directory.

### 2. Startup Application
Use the user's requested startup command from the backend folder level. We specify the Spring Active Profile (`prod`) to lock away development properties.

```bash
sudo java -jar target/backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

*(Note: During startup, Flyway will naturally process all migration tables inside `/db/migration/`, preparing the architecture beforehand, and Spring Boot will commence serving port `8080` patiently waiting for Nginx's re-routes.)*

---

**Last Updated:** March 2026
**Repository:** https://github.com/jasanchezmartel/Sustainable-Island---Las-Palmas
**Author:** Sustainable Island Team6
