# Use the official OpenJDK image as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and build scripts into the container
COPY gradlew ./
COPY gradle ./gradle

# Copy the application source code and build scripts into the container
COPY src ./src
COPY build.gradle.kts ./
COPY settings.gradle.kts ./
COPY gradle.properties ./

# Copy the other necessary files (e.g., .gitignore, README.md)
COPY . .

# Make the Gradle wrapper script executable
RUN chmod +x ./gradlew

# Run the application using the Gradle wrapper
CMD ["./gradlew", "run"]
