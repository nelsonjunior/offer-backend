FROM openjdk:17-jdk-slim

HEALTHCHECK --interval=5m --timeout=3s CMD curl -f http://localhost:80/actuator/health/ || exit 1

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]