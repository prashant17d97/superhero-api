# Use a base image with the Java Runtime Environment (JRE)
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/*.jar app.jar

# Set the entry point to run the Java application
ENTRYPOINT ["java", "-jar", "app.jar"]