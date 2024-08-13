FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/superheros-api-all.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
