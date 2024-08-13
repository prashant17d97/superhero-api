# Build stage
FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

# Final stage
FROM openjdk:11
EXPOSE 8080  # Expose the port on which Ktor will run
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/superheros-api-all.jar /app/superheros-api-all.jar
COPY --from=build /home/gradle/src/src/main/resources/application.conf /app/application.conf
WORKDIR /app
ENTRYPOINT ["java", "-jar", "superheros-api-all.jar"]
