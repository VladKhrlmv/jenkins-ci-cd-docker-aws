FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENV SERVER_PORT=8080 \
    MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE=api/hello

ENTRYPOINT ["java", "-jar", "app.jar"]
