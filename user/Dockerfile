FROM eclipse-temurin:17-jdk-alpine

ARG WAR_FILE=./target/*.jar

COPY ${WAR_FILE} user.jar

CMD ["java", "-jar", "user.jar"]