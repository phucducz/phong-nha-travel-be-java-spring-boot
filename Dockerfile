#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
#
# Package stage
#
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/phongnhatravelbackendver2-0.0.1-SNAPSHOT.jar phongnhatravelbackendver2.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","phongnhatravelbackendver2-SNAPSHOT.jar"]
