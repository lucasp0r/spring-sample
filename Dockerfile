# Build stage
FROM maven:3.8.1 AS build
COPY src /tmp/src
COPY pom.xml /tmp/pom.xml
WORKDIR /tmp/
RUN mvn clean install -DskipTests


# Package stage

FROM openjdk:11
COPY --from=build /tmp/target/spring-project.jar spring-project.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/spring-project.jar"]