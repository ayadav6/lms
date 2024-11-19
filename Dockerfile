# Step 1: Build the Spring Boot app
FROM openjdk:21-jdk-slim AS build

WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# Copy the source code
COPY src ./src

# Give execution permission to mvnw script
RUN chmod +x mvnw

# Run Maven to build the app
RUN ./mvnw clean package -DskipTests

# Debug step to check if the jar file is created
RUN ls -l /app/target/

# Step 2: Use a lightweight image to run the app
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/lms-0.0.1-SNAPSHOT.jar lms-app.jar

# Expose the port your app will run on
EXPOSE 8081

# Command to run the app
CMD ["java", "-jar", "lms-app.jar"]
