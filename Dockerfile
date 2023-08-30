# Use an official OpenJDK base image
FROM openjdk:8-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR into the container
COPY build/libs/customers-garage-backend.jar customers-garage-backend.jar

# Expose the port your Spring Boot app is listening on
EXPOSE 8081

# Run the Spring Boot application
CMD ["java", "-jar", "customers-garage-backend.jar"]
