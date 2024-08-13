FROM gradle:7-jdk11 AS build
COPY --chown=gradle: gradle. /home/gradle/sro
WORKDIR /home/ gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjak:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/superheros-api.jar
ENTRYPOINT ["java", "-jar", "/app/superheros-api.jar"]