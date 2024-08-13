FROM openjdk:17-jdk-alpine as build

WORKDIR /app

COPY build.gradle settings.gradle ./

RUN ./gradlew shadowJar

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/superheros-api-all.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

