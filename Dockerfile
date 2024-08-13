# Build stage
FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

# Final stage
FROM openjdk:11
# Expose port 8080 (the port Ktor will run on)
EXPOSE 8080
RUN mkdir /app
# Copy the fat JAR file from the build stage to /app directory
COPY --from=build /home/gradle/src/build/libs/superheros-api-all.jar /app/superheros-api-all.jar
WORKDIR /app
# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "superheros-api-all.jar"]
RUN ./gradlew run
