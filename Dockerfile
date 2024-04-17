# Use Ubuntu with OpenJDK 21 for build stage
FROM ubuntu:latest AS builder

# Update package list
RUN apt-get update

# Install OpenJDK 21
RUN apt-get install openjdk-21-jdk -y

# Install Maven
RUN apt-get install maven -y

# Copy the project directory
WORKDIR /app

COPY . .

# Build the JAR with Maven
RUN mvn package

# Switch to a slimmer runtime image with OpenJDK 21
FROM openjdk:21-jdk-slim

# Expose port 1243
EXPOSE 1243

# Copy the JAR from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]