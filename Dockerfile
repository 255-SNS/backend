# Base image with OpenJDK 21
FROM openjdk:21-jdk-slim

ARG JAR_FILE=build/libs/sns-255-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]