#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package

#
# Package stage
#
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","phongnhatravelbackendver2-0.0.1-SNAPSHOT.jar"]
