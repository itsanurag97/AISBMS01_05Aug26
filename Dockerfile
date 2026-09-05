# Stage 1: Build Stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Dependency Caching Step
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy Source and Package JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime Stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy executable JAR file from build stage
COPY --from=build /app/target/StudentRegistrationService-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]