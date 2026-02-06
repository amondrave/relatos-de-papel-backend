# syntax=docker/dockerfile:1
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
ENV PORT=8732
COPY --from=build /app/target/ms-books-payments-1.0.0.jar app.jar
EXPOSE 8732
ENTRYPOINT ["java","-jar","/app/app.jar"]
