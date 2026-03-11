# Sustainable Island Backend - Implementation Documentation

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![MySQL](https://img.shields.io/badge/MySQL-9.4-orange)
![JWT](https://img.shields.io/badge/JWT-Auth-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📋 Index
- [Overview](#-overview)
- [Technologies](#-technologies-implemented)
- [Implemented Fixes](#-implemented-fixes)
- [Data Model](#-data-model)
- [Rate Limiting](#-rate-limiting-features)
- [Logging Configuration](#-logging-configuration)
- [Main Endpoints](#-main-endpoints)
- [Security Configuration](#-security-configuration)
- [Fixed Errors](#-fixed-errors)
- [Environment Variables](#-environment-variables-env-file-example)
- [How to Run](#-how-to-run)
- [Next Steps](#-next-steps--improvements)
- [References](#-references)
- [Contributors](#-contributors)

## 📋 Overview
Documentation of fixes and improvements implemented in the Sustainable Island backend, a Spring Boot application with JWT authentication, rate limiting, and database management.

## 🚀 Technologies Implemented

- **Spring Boot 3.4.3** - Core framework
- **Spring Security** - Authentication and authorization
- **JWT** - Authentication tokens
- **JPA/Hibernate** - ORM and persistence
- **MySQL** - Database
- **Lombok** - Boilerplate code reduction
- **Bucket4j** - Rate limiting
- **Flyway** - Database migrations

## 🔧 Implemented Fixes

### 1. Missing Dependencies
The following dependencies were added to `pom.xml`:

```xml
<!-- LOMBOK -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- BUCKET4J -->
<dependency>
    <groupId>com.bucket4j</groupId>
    <artifactId>bucket4j-core</artifactId>
    <version>8.7.0</version>
</dependency>

<!-- FLYWAY -->
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-mysql</artifactId>
</dependency>
```

#### application-prod.properties (Production)
```properties
# ===== Application Name =====
spring.application.name=SeaZen-Prod

# ===== MySQL Database (Production) =====
spring.datasource.url=jdbc:mysql://${PROD_DB_HOST}:${PROD_DB_PORT}/${PROD_DB_NAME}?useSSL=true&requireSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=false
spring.datasource.username=${PROD_DB_USER}
spring.datasource.password=${PROD_DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ===== Hikari Connection Pool (optimized for production) =====
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.connection-test-query=SELECT 1

# ===== JPA / Hibernate (Production) =====
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=false
spring.jpa.properties.hibernate.use_sql_comments=false
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true

# ===== Flyway Configuration =====
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.clean-disabled=true
spring.flyway.validate-on-migrate=true
spring.flyway.locations=classpath:db/migration,classpath:db/seed
spring.flyway.out-of-order=false

# ===== JWT Configuration (Production) =====
application.security.jwt.secret-key=${JWT_SECRET_KEY}
application.security.jwt.expiration=86400000
application.security.jwt.refresh-token.expiration=604800000

# ===== Server Configuration =====
server.port=${PORT:8080}
server.error.whitelabel.enabled=false
server.error.path=/error
server.compression.enabled=true
server.compression.mime-types=application/json,application/xml,text/html,text/plain

# ===== CORS Configuration (Production) =====
cors.allowed-origins=${CORS_ALLOWED_ORIGINS:https://yourdomain.com,https://www.yourdomain.com}
cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
cors.allowed-headers=Authorization,Content-Type,X-Requested-With,Accept
cors.allow-credentials=true
cors.max-age=3600

# ===== File Upload Configuration =====
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB
spring.servlet.multipart.enabled=true
spring.servlet.multipart.location=${TEMP_DIR:/tmp}

# ===== File Storage =====
app.file-upload-dir=${FILE_UPLOAD_DIR:/var/data/seazen/uploads}

# ===== Logs Configuration (Production) =====
logging.level.root=WARN
logging.level.com.sustainableisland.seazen=INFO
logging.level.org.springframework.web=WARN
logging.level.org.hibernate=WARN
logging.level.org.hibernate.SQL=WARN
logging.level.org.springframework.security=WARN

logging.pattern.console={"timestamp":"%d{yyyy-MM-dd'T'HH:mm:ss.SSSZ}","level":"%p","thread":"%t","logger":"%c","message":"%m","trace":"%X{traceId}","span":"%X{spanId}"}%n

logging.file.name=/var/log/seazen/application.log
logging.logback.rollingpolicy.file-name-pattern=/var/log/seazen/application.%d{yyyy-MM-dd}.%i.log
logging.logback.rollingpolicy.rollover-on-startup=false
logging.logback.rollingpolicy.max-file-size=50MB
logging.logback.rollingpolicy.max-history=30
logging.logback.rollingpolicy.total-size-cap=1GB

# ===== Actuator (Monitoring) =====
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=never
management.endpoint.health.probes.enabled=true
management.health.db.enabled=true
management.health.diskspace.enabled=true

# ===== Additional Security =====
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.secure=true
server.servlet.session.cookie.same-site=lax
server.servlet.session.timeout=30m
```


### 3. RateLimitingInterceptor Fix
Problem: Deprecated methods in Bucket4j 8.7.0
Solution: Updated to new builder API

```java
package com.sustainableisland.seazen.middleware;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RateLimitingInterceptor implements HandlerInterceptor {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String clientId = getClientId(request);
        Bucket bucket = buckets.computeIfAbsent(clientId, this::createNewBucket);

        if (bucket.tryConsume(1)) {
            return true;
        } else {
            response.setStatus(429);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Too many requests. Please try again later.\"}");
            return false;
        }
    }

    private String getClientId(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "user:" + request.getUserPrincipal().getName();
        }
        
        String ip = request.getRemoteAddr();
        if (ip == null || ip.isEmpty() || "0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return "ip:" + ip;
    }

    private Bucket createNewBucket(String clientId) {
        Bandwidth limit = Bandwidth.builder()
                .capacity(10)
                .refillGreedy(10, Duration.ofMinutes(1))
                .build();
        
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
}
```


### 4. WebConfig with Logging Interceptor
```java
package com.sustainableisland.seazen.config;

import com.sustainableisland.seazen.middleware.RateLimitingInterceptor;
import com.sustainableisland.seazen.middleware.RequestLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RateLimitingInterceptor rateLimitingInterceptor;
    
    @Autowired
    private RequestLoggingInterceptor requestLoggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Rate limiting interceptor for all API endpoints
        registry.addInterceptor(rateLimitingInterceptor)
                .addPathPatterns("/api/**");
        
        // Logging interceptor - excludes auth endpoints for security
        registry.addInterceptor(requestLoggingInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/v1/auth/**");
    }
}
```


### 5. RequestLoggingInterceptor
```java
package com.sustainableisland.seazen.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("Request: {} {} from IP: {}", 
            request.getMethod(), 
            request.getRequestURI(),
            request.getRemoteAddr());
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                               Object handler, Exception ex) {
        logger.info("Response: {} for {} {} - Status: {}", 
            response.getStatus(),
            request.getMethod(),
            request.getRequestURI());
    }
}
```


## 📊 Data Model

### Automatically Created Tables

### Package Structure
```text
├── SeaZen.java (main class)
├── config/
│   ├── ApplicationConfig.java
│   ├── SecurityConfig.java
│   └── WebConfig.java
├── controllers/
│   ├── AnimalController.java
│   ├── AuthController.java
│   ├── NotificationController.java
│   ├── TaskController.java
│   └── UserController.java
├── dtos/
│   ├── AnimalDTO.java
│   ├── ApiResponse.java
│   ├── AuthenticationRequest.java
│   ├── AuthenticationResponse.java
│   ├── NotificationDTO.java
│   ├── RegisterRequest.java
│   ├── TaskDTO.java
│   └── UserDTO.java
├── exceptions/
│   └── GlobalExceptionHandler.java
├── middleware/
│   ├── RateLimitingInterceptor.java
│   └── RequestLoggingInterceptor.java
├── models/
│   ├── Animal.java
│   ├── AnimalStatus.java
│   ├── Notification.java
│   ├── Task.java
│   └── User.java
├── repositories/
│   ├── AnimalRepository.java
│   ├── AnimalStatusRepository.java
│   ├── NotificationRepository.java
│   ├── TaskRepository.java
│   └── UserRepository.java
└── security/
    ├── JwtAuthenticationFilter.java
    └── JwtService.java
```

## 🔒 Rate Limiting Features
- 10 requests per minute per client
- Client identification by:
  - JWT token (authenticated users)
  - IP address (anonymous users)
- HTTP 429 response when limit exceeded

## 📝 Logging Configuration

### Security Considerations
Authentication endpoints are excluded from logging to protect sensitive data:
```java
.excludePathPatterns("/api/v1/auth/**") // Protects credentials and tokens
```

### Log Rotation
- Daily log rotation
- Maximum file size: 10MB (dev) / 50MB (prod)
- History retention: 14 days (dev) / 30 days (prod)
- JSON format in production for monitoring systems

## 🚦 Main Endpoints
| Method | Endpoint | Description | Rate Limit |
|--------|----------|-------------|------------|
| POST | `/api/v1/auth/login` | Authentication | Excluded |
| POST | `/api/v1/auth/register` | Registration | Excluded |
| POST | `/api/v1/auth/refresh` | Token refresh | Excluded |
| GET | `/api/v1/animals` | List animals | 10/min |
| POST | `/api/v1/animals` | Create animal | 10/min |
| GET | `/api/v1/animals/{id}` | Get animal | 10/min |
| PUT | `/api/v1/animals/{id}` | Update animal | 10/min |
| DELETE | `/api/v1/animals/{id}` | Delete animal | 10/min |
| GET | `/api/v1/tasks` | List tasks | 10/min |
| POST | `/api/v1/tasks` | Create task | 10/min |
| GET | `/api/v1/notifications` | Get notifications | 10/min |

## 🔐 Security Configuration

### JWT Authentication
```java
// JWT properties
application.security.jwt.secret-key=${JWT_SECRET_KEY}
application.security.jwt.expiration=86400000 // 24 hours
application.security.jwt.refresh-token.expiration=604800000 // 7 days
```

### Security Chain Configuration
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/v1/auth/**").permitAll()
            .requestMatchers("/error").permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
}
```

## 🐛 Fixed Errors
| Error | Cause | Solution |
|-------|-------|----------|
| `package lombok does not exist` | Missing dependency | Added Lombok to pom.xml |
| `package io.github.bucket4j does not exist` | Missing dependency | Added Bucket4j to pom.xml |
| `Schema-validation: missing table [animal_status]` | Table doesn't exist | Changed to `ddl-auto=update` |
| `Methods deprecated in Bucket4j` | Obsolete API | Updated to builder pattern |
| `Package mismatch in interceptor` | Wrong package | Moved to correct `middleware` package |
| `The declared package does not match the expected package` | Wrong package declaration | Fixed package declaration in RateLimitingInterceptor |

## 📦 Environment Variables (.env file example)
```properties
# Database
PROD_DB_HOST=localhost
PROD_DB_PORT=3306
PROD_DB_NAME=seazen_prod
PROD_DB_USER=seazen_user
PROD_DB_PASSWORD=YourStrongPassword123!

# JWT
JWT_SECRET_KEY=your-very-long-secret-key-for-jwt-tokens-with-at-least-32-characters

# CORS
CORS_ALLOWED_ORIGINS=https://yourdomain.com,https://admin.yourdomain.com

# File Upload
FILE_UPLOAD_DIR=/var/data/seazen/uploads

# SSL (optional)
# SSL_KEY_STORE_PATH=/etc/ssl/seazen/keystore.p12
# SSL_KEY_STORE_PASSWORD=your-keystore-password
```

## 🚀 How to Run

### Development
```bash
# Clean and compile
./mvnw clean compile

# Run with development profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Or directly (uses default profile)
./mvnw spring-boot:run
```

### Production
```bash
# Load environment variables (Linux/Mac)
export $(cat .env-prod | xargs)

# Load environment variables (Windows PowerShell)
Get-Content .env-prod | ForEach-Object {
    if ($_ -match '^([^=]+)=(.*)$') {
        [Environment]::SetEnvironmentVariable($matches[1], $matches[2])
    }
}

# Run with production profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod

# Or build and run JAR
./mvnw clean package -Pprod
java -jar target/backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## 📈 Next Steps or Improvements we can make
- [ ] **Redis Cache** - Improve performance with caching
- [ ] **WebSockets** - Real-time notifications
- [ ] **Swagger/OpenAPI** - API documentation
- [ ] **Unit & Integration Tests** - Code coverage
- [ ] **Docker** - Containerization
- [ ] **CI/CD Pipeline** - Continuous integration
- [ ] **Metrics & Monitoring** - Prometheus + Grafana
- [ ] **Audit Logging** - Track important operations

## 📚 References
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/)
- [Bucket4j Documentation](https://bucket4j.com/)
- [JWT.io](https://jwt.io/)
- [Flyway Documentation](https://flywaydb.org/documentation/)
- [Hibernate ORM](https://hibernate.org/orm/documentation/)

## 👥 Contributors
- **Team 6 - Sustainable Island Development**
  - Juan Sánchez Martel
  - [Other team members]

---

**Last Updated:** March 8, 2026
**Version:** 1.0.0
**Repository:** https://github.com/jasanchezmartel/Sustainable-Island---Las-Palmas
**Author:** Sustainable Island Team6