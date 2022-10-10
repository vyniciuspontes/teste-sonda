FROM maven:3.8.3-openjdk-17 AS build
COPY src ./src
COPY pom.xml .
RUN mvn -f ./pom.xml clean package

FROM openjdk:17
VOLUME /target
COPY --from=build ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]