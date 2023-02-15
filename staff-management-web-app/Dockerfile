# syntax=docker/dockerfile:1
FROM openjdk:latest

WORKDIR /app
COPY /target/staff-management-web-app-0.0.1-SNAPSHOT.jar .
CMD java -jar staff-management-web-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080/tcp