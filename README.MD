<h1>Assignment: Emergency Call Management System</h1>
This example application uses Java 21, Spring-Boot, PostgreSQL database

# Things that this app missing due to time limits (but the author is aware of)
- There is no token refresh method
- Security and business logic should be in separate module
- Storing passwords plain text
- Due to simplicity there is no unit tests only end-to-end tests
- End-to-end tests include only main (successful) test cases
- Not all kind of errors are properly handled
- Proper logging is missing
- Might be issues with validation
- Docker-compose should be improved
- Swagger require more configuration
- Fake/test data is included in migration scripts to simplify testing


# How to run this project locally (tested on Windows 11 and Docker Desktop)
Application requires java 21, JAVA_HOME env variable set and installed and running docker/docker-compose <br />
From inside ecms main directory build project using maven:

```.\mvnw clean package -DskipTests```

Start PostgreSQL via docker-compose

```docker-compose up -d```

Migrate database using flyway maven plugin

```.\mvnw flyway:migrate```

Start application using spring-boot maven plugin

```.\mvnw spring-boot:run```

Swagger at:

```/swagger-ui/index.html```