# Build stage
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./gradlew shadowJar --no-daemon

# Runtime stage
FROM openjdk:17-jdk-slim

EXPOSE 8080

# Copy the built JAR from the build stage
COPY --from=build /build/libs/superheros-api-all.jar /app.jar

# Environment variable for PORT
ENV PORT=8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
