# Build stage
FROM maven:3.8.1 AS build
RUN addgroup -S xyzgroup
RUN adduser -S -D -h /spring-project appuser xyzgroup
RUN chown -R appuser:xyzgroup /tmp/*
COPY src /tmp/src
COPY pom.xml /tmp/pom.xml
WORKDIR /tmp/
RUN mvn clean install -DskipTests


# Package stage

FROM openjdk:11
RUN addgroup -S xyzgroup
RUN adduser -S -D -h /spring-project appuser xyzgroup
RUN chown -R appuser:xyzgroup /tmp/*
COPY --from=build /tmp/target/spring-project.jar spring-project.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/spring-project.jar"]
