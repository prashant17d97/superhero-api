# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the fat JAR into the container
COPY build/libs/superheros-api-all.jar /app/superheros-api-all.jar

# Set the command to run your application
CMD ["java", "-jar", "superheros-api-all.jar"]
