FROM eclipse-temurin:17-jdk-alpine

ARG WAR_FILE=./target/*.jar

COPY ${WAR_FILE} donation.jar

CMD ["java", "-jar", "donation.jar"]