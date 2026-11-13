# MAVEN

Maven is a project management and build automation tool.  
- It is used to **build projects** and manage dependencies.  
- It ensures the correct **project structure**.  
- It can **compile, package, test, and deploy** projects.  
- It provides access to valuable dependencies like **Jackson, JDBC, Hibernate, Spring, JUnit**, etc.  

### Installing & Setting up a Maven Project  

**1. Install Maven (if not already installed):**  
- Download from [Maven official site](https://maven.apache.org/download.cgi).  
- Extract and set the `MAVEN_HOME` environment variable.  
- Add Maven’s `bin` folder to the system PATH.  
- Verify installation:  
  ```bash
  mvn -version
  ```

**2. Create a New Maven Project:**
Using command line:
```
mvn archetype:generate -DgroupId=com.example \
    -DartifactId=my-app \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DinteractiveMode=false
```
Or use IDE support (e.g., IntelliJ, Eclipse, VS Code) → Select New Maven Project.

**3. Project Structure:**
Maven will create a standard project structure:
```
my-app/
 ├── src/
 │   ├── main/java   → Application source code
 │   └── test/java   → Unit test code
 ├── pom.xml         → Maven configuration file
```

**4. pom.xml:**
This is the core configuration file where you define dependencies and plugins.
*Example:*
```
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- Example: Jackson for JSON -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.17.0</version>
        </dependency>
    </dependencies>
</project>
```

**5. Common Maven Commands:**
```
mvn clean            # cleans target/ folder
mvn compile          # compiles source code
mvn test             # runs tests
mvn package          # builds a .jar or .war file
mvn install          # installs into local repository (~/.m2)
mvn dependency:tree  # shows dependency hierarchy
```

### Common Dependencies for Fullstack Development

Below are widely used Maven dependencies with their use cases:
#### **Backend & APIs**

**Spring Boot** → Build REST APIs, backend services, full applications.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.3.0</version>
</dependency>
```
**Spring Data JPA** → Simplifies database interaction using ORM (with Hibernate).
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <version>3.3.0</version>
</dependency>
```
- **Hibernate** → Direct ORM framework for database mappings if not using Spring Boot.  
- **Jackson (Fasterxml)** → JSON parsing & serialization.  
- **Gson (Google)** → Alternative JSON library.  

#### **Databases & Persistence**

**PostgreSQL Driver** → Connect Java apps to PostgreSQL.
```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```
- **MySQL Driver** → Connect to MySQL/MariaDB.  
- **Flyway / Liquibase** → Database schema migrations and version control.  

#### **Security & Authentication**

- **Spring Security** → Authentication, authorization, role-based access.  

**JWT (JSON Web Token)** → For secure stateless authentication.
```
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
```

#### **Testing**

- **JUnit 5** → Unit testing framework.  
- **Mockito** → Mocking framework for testing.  
- **Spring Boot Starter Test** → Bundled testing tools.  
- **Postman** → For API testing.

#### **Utility Libraries**

- **Apache Commons Lang** → Useful utilities for Strings, Numbers, Dates, etc.  

**Lombok** → Reduces boilerplate (getters, setters, constructors).
```
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.32</version>
    <scope>provided</scope>
</dependency>
```
#### **Frontend Integration**

- **Thymeleaf** → Server-side HTML templating for full-stack apps.  
- **Spring Boot Starter Websocket** → Real-time communication (chat apps, live notifications).  
