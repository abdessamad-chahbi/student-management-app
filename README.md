```markdown
# 🎓 Web Application for Student Management

> 🎯 Designed and developed a Spring Boot web application for managing student data with secure role-based access. Integrated Thymeleaf, Bootstrap, MongoDB, and REST APIs for CRUD operations.

---

## Table of contents

- Project Overview
- Features
- Technologies
- Prerequisites
- MongoDB (Windows) — Install & Data Import
- Configuration
- Build & Run (local)
- Endpoints & UI
- Tests
- Troubleshooting & Tips
- Contributing
- License
- Contact

---

## Project overview

This is a Java Spring Boot web application that provides CRUD management for students and directors. It uses Thymeleaf for server-side rendering and Bootstrap for responsive styling. Data persistence uses MongoDB via Spring Data MongoDB. Authentication and role-based pages are included (login templates and controllers).

---

## Features

- ✅ CRUD for Students and Directors  
- 🔐 Role-based access / login pages  
- 🌿 Thymeleaf templates (server-side views)  
- 📡 REST API endpoints for programmatic access  
- 🎨 Bootstrap-based responsive UI  
- 🛠 Maven build with Maven Wrapper included

---

## Technologies

- ☕ Java (backend)
- 🌱 Spring Boot
- 🔁 Spring Data (Spring Data MongoDB)
- 🍃 MongoDB
- 🍂 Thymeleaf
- 🎨 Bootstrap
- 🧩 HTML
- 🎨 CSS
- 🧰 Maven (with Maven Wrapper)

(Icon classes available in repository metadata: logos-java, simple-icons:springboot, simple-icons:spring, logos-mongodb, simple-icons:thymeleaf, logos-bootstrap, logos-html-5, logos-css-3)

---

## Prerequisites

- Java JDK 17+ (check `pom.xml` for exact required version)  
- Git  
- Maven (optional — use included Maven Wrapper `./mvnw` / `mvnw.cmd`)  
- MongoDB (see installation instructions below)  
- (Optional) Docker for running MongoDB during development

---

## MongoDB (Windows) — Install & Data Import (step-by-step) 🗂️

These instructions follow the steps you provided for MongoDB Community Server 3.4.24.

1. Download MongoDB Community Server:
   - https://www.mongodb.com/try/download/community  
   - Select version `3.4.24` and the Windows package.

2. Install MongoDB using the installer.

3. Create data directory on drive C:
   - Create folder: `C:\data\db`

4. Start MongoDB server (Terminal 1 - Server):
   ```cmd
   cd "C:\Program Files\MongoDB\Server\3.4\bin"
   mongod
   ```
   Or specify dbpath explicitly:
   ```cmd
   mongod --dbpath "C:\data\db"
   ```

5. Open Mongo shell (Terminal 2 - Client):
   ```cmd
   cd "C:\Program Files\MongoDB\Server\3.4\bin"
   mongo
   ```

6. Default connection URI:
   ```
   mongodb://127.0.0.1:27017
   ```

7. Create / use the database:
   ```js
   show dbs;
   use myDB_Spring_MongoDB;
   ```

8. Import sample data with `mongoimport`:
   ```cmd
   mongoimport --db myDB_Spring_MongoDB --collection Directeur --file directeur.json
   mongoimport --db myDB_Spring_MongoDB --collection Etudiant --file etudiant.json --jsonArray
   ```
   If running from the MongoDB `bin` folder:
   ```cmd
   "C:\Program Files\MongoDB\Server\3.4\bin\mongoimport" --db myDB_Spring_MongoDB --collection Etudiant --file etudiant.json --jsonArray
   ```

9. Verify imported data:
   ```js
   use myDB_Spring_MongoDB;
   db.Directeur.find().pretty();
   db.Etudiant.find().pretty();
   ```

Helpful video (reference): https://youtu.be/UJQiGBDKXY0?si=NCdd9XjEJmf91B08

---

## Configuration

Create or edit `src/main/resources/application.properties` with your MongoDB URI and server port:

```properties
# src/main/resources/application.properties
spring.data.mongodb.uri=mongodb://127.0.0.1:27017/myDB_Spring_MongoDB
server.port=8080
spring.thymeleaf.cache=false
```

You can also set the `SPRING_DATA_MONGODB_URI` environment variable.

---

## Build & Run (local) 🏁

Build with the Maven Wrapper:

Unix / macOS:
```bash
./mvnw clean package
```

Windows (CMD / PowerShell):
```cmd
mvnw.cmd clean package
```

Run the packaged jar:
```bash
java -jar target/<artifactId>-<version>.jar
```

Or run directly with Spring Boot plugin:
```bash
./mvnw spring-boot:run
```

Open the app in your browser:
```
http://localhost:8080
```

---

## Endpoints & UI (examples) 🔎

Server-side pages (Thymeleaf templates):
- `/` — Home / login
- `/login` — Login page
- `/directeur` — Director main interface (template: `InterfacePrincipaleDirecteur.html`)
- `/etudiant` — Student pages

REST API examples:
- `GET /api/directeurs`
- `GET /api/etudiants`
- `POST /api/etudiant`
- `PUT /api/etudiant/{id}`
- `DELETE /api/etudiant/{id}`

See controller classes under `src/main/java/appSpringMongo` for exact mappings.

---

## Tests 🧪

Run tests:
```bash
./mvnw test
```

For testing, add:
- Unit tests with JUnit 5 + Mockito
- Integration tests with Spring Boot Test
- Use Testcontainers for MongoDB integration tests (recommended)

---

## Troubleshooting & Tips ⚠️

- If `mongod` fails, ensure `C:\data\db` exists and has correct permissions.  
- Use `--jsonArray` with `mongoimport` if your JSON file contains an array.  
- If using OneDrive, be careful of file syncing/locking issues on Windows.  
- Add `.classpath`, `.project`, `.settings` to `.gitignore` if you don't want IDE metadata in the repo.

---

## Contributing 🤝

1. Fork the repo  
2. Create a branch: `feature/your-feature`  
3. Implement changes and add tests  
4. Run `./mvnw clean verify` locally  
5. Open a PR with a clear description

---

## License 📜

---

## 🎥 Demo

Watch the project demo here:  
➡️ [Click to view Demo Video](https://github.com/abdessamad-chahbi/student-management-app/blob/main/Demo.mp4)


---

## Contact 📫

Repository: https://github.com/abdessamad-chahbi/student-management-app  
Author: `abdessamad-chahbi`

---
```
