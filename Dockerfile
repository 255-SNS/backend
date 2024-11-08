# Base image with OpenJDK 11
FROM openjdk:11-jre-slim

# Application JAR file
ARG JAR_FILE=build/libs/hello-java-0.0.1-SNAPSHOT.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
