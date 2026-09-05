# Stage 1: Build Stage using OpenJDK 21 & Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy configuration and source files
COPY pom.xml .
COPY src ./src

# Build the WAR package skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Runtime Stage using OpenJDK 21
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the generated WAR file from build stage
COPY --from=build /app/target/StudentRegistrationService-0.0.1-SNAPSHOT.war app.war

# Expose port (Spring Boot default port 8080)
EXPOSE 8080

# Execute the application
ENTRYPOINT ["java", "-jar", "app.war"]