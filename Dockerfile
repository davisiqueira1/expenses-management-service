FROM maven:3.8.5-openjdk-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY .env /app/

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
