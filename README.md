# SeaZen

## About this project

This repository constitutes a project that mix all our subjects of the Higher-Level Training Cycle in Web Application
Development
that we are currently completing. The project consists of a full-stack application, meaning it encompasses an entire
server infrastructure that defines its existence, logic, and storage, ensuring complete, robust, and proper
functionality based on configurations we decided upon collectively.

## What does SeaZen do?

It's an app designed to encourage people to pick up trash on beaches by adopting virtual animals in need of care.

This allows us to promote environmental preservation by saving marine life in numerous locations and with the participation of people of all ages.

## Technologies Used

* Backend: [![SpringBoot](https://img.shields.io/badge/-Spring_Boot-6DB33F?style=flat&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
* Migrations & seeders: [![FlyWay](https://img.shields.io/badge/-Flyway-CC0200?style=flat&logo=flyway&logoColor=white)](https://flywaydb.org/)
* ORM: [![Hibernate](https://img.shields.io/badge/-Hibernate-59666C?style=flat&logo=hibernate&logoColor=white)](https://hibernate.org/)
* Database: [![MySQL](https://img.shields.io/badge/-MySQL-4479A1?style=flat&logo=mysql&logoColor=white)](https://www.mysql.com/)
* Containerization: [![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat&logo=docker&logoColor=white)](https://docs.docker.com/desktop/setup/install/windows-install/)
* Frontend: [![ReactJS](https://img.shields.io/badge/-ReactJS-09D3AC?style=flat&logo=createreactapp&logoColor=white)](https://es.react.dev/)
* API Endpoints: [![PostMan](https://img.shields.io/badge/-Postman-FF6C37?style=flat&logo=postman&logoColor=white)](https://www.postman.com/)
* Backend deploy: [![Isard](<img width="810" height="645" alt="image" src="https://github.com/user-attachments/assets/35ec88f2-ef5e-4a63-828d-69d0b6b9e039" />
)](https://escritorios.ieselrincon.es/login)
* Frontend deploy: 
* Interface design: [![Figma](https://img.shields.io/badge/-Figma-F24E1E?style=flat&logo=figma&logoColor=white)](https://www.figma.com/)
* Versions control (Github Desktop FrontEnd Side / Sourcetree Backend Side): [![GithubDesktop]] & [![Sourcetree](https://img.shields.io/badge/-Sourcetree-0052CC?style=flat&logo=sourcetree&logoColor=white)](https://www.sourcetreeapp.com/)

## Diagrams and deployment documentation

- [![Docs](https://img.shields.io/badge/-Docs-437291?style=flat&logoColor=white)]([link])

# Backend

### Requirements

To run the **backend** of the application, the user must have installed:

- [![JDK 17](https://img.shields.io/badge/-JDK_17-437291?style=flat&logo=openjdk&logoColor=white)](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat&logo=docker&logoColor=white)](https://docs.docker.com/desktop/setup/install/windows-install/)

### Instructions to run the backend

1. **Clone the repository**:

```bash
git clone https://github.com/jasanchezmartel/Sustainable-Island---Las-Palmas.git
```

2. Run the .bat file in the repository. This file will automatically configure the ```application.properties``` file to
   prepare the backend environment.

3. Verify if in Docker Desktop there is a new container. You can see that is automatically started.

4. Start Spring Boot with the following command:

```bash
 ./mvnw spring-boot:run
 ```

## Production Backend

> Full details: [`docs/Prod-Backend.md`](docs/Prod-Backend.md)

### Request flow

`External Client (HTTPS)` → `Nginx (Reverse Proxy + SSL :443)` → `Spring Boot (:8080)` → `MySQL (:3307)`

### Requirements

Before deploying to production, the server must have:

- [![JDK 17](https://img.shields.io/badge/-JDK_17-437291?style=flat&logo=openjdk&logoColor=white)](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) installed on the host.
- [![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat&logo=docker&logoColor=white)](https://docs.docker.com/desktop/setup/install/windows-install/) to spin up the MySQL container.
- **Nginx** installed and configured with a valid SSL certificate (`fullchain.pem` / `privkey.pem`).
- Log directory created and accessible:

  ```bash
  sudo mkdir -p /var/log/seazen
  sudo chown -R $USER:$USER /var/log/seazen
  ```

- MySQL database initialised:

  ```sql
  CREATE DATABASE seazen;
  ```

### Instructions to deploy the backend in production

1. **Clone the repository** (if not already cloned):

   ```bash
   git clone https://github.com/jasanchezmartel/Sustainable-Island---Las-Palmas.git
   ```

2. **Start the MySQL container** using the provided script from the `backend/` folder:

   ```bash
   ./docker-start-containter-mysql.bat
   ```

   Verify in Docker Desktop that the container is running on port **3307**.

3. **Install and configure Nginx** — copy the provided configuration file and reload:

   ```bash
   sudo cp nginx-sustainable-island.conf /etc/nginx/sites-available/sustainable-island
   sudo ln -s /etc/nginx/sites-available/sustainable-island /etc/nginx/sites-enabled/
   sudo systemctl reload nginx
   ```

4. **Compile and package** the application (from `backend/`):

   ```bash
   ./mvnw clean package -DskipTests
   ```

   After a `BUILD SUCCESS`, the executable `target/backend-0.0.1-SNAPSHOT.jar` will be ready.

5. **Start the application** with the production profile:

   ```bash
   sudo java -jar target/backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
   ```

   > During startup, Flyway will automatically run all migrations in `/db/migration/` and Spring Boot will start listening on port **8080**, waiting for Nginx to forward traffic.

---

# Frontend


## Requirements

## Project Links

* [![Figma](https://img.shields.io/badge/-Figma-F24E1E?style=flat&logo=figma&logoColor=white)]([https://www.figma.com/design/b1wzQ5d4tamVixL3SZzZrg/Blood4Life?node-id=0-1&p=f&t=4vENweVa6vCEpXQd-0](https://www.figma.com/design/XrEDllgQe1w5IeMsC1ODnW/Las-Palmas?node-id=51-122&p=f))
* [![GitHub Project](https://img.shields.io/badge/-Github_Project-181717?style=flat&logo=github&logoColor=white)]([https://github.com/jasanchezmartel/Sustainable-Island---Las-Palmas.git])
* [![PostMan](https://img.shields.io/badge/-Postman-FF6C37?style=flat&logo=postman&logoColor=white)](https://documenter.getpostman.com/view/49710299/2sBXietaPk)

## Authors

| Name         | Surname           |
|--------------|-------------------|
| Miranda      |                   |
| Oliver       |                   |
| Juan Antonio | Sánchez Martel    |
