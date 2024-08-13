FROM gradle:7-jdk11 AS build

# Copy necessary files and set permissions
COPY --chown=gradle:gradle gradle/ /home/gradle/sro

# Set working directory
WORKDIR /home/gradle/src

# Run Gradle build command and capture output for debugging
RUN gradle buildFatJar --no-daemon || { echo 'Gradle build failed'; exit 1; }

FROM openjdk:11

# Expose port
EXPOSE 8080

# Create application directory
RUN mkdir /app

# Copy the JAR file from the build stage
COPY --from=build /home/gradle/src/build/libs/*.jar /app/superheros-api.jar

# Set entry point
ENTRYPOINT ["java", "-jar", "/app/superheros-api.jar"]
