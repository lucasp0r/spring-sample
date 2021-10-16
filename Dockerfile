FROM openjdk:11
FROM maven:alpine

# image layer
WORKDIR /spring-project
ADD pom.xml /spring-project
RUN mvn verify clean --fail-never

# Image layer: with the application
COPY . /spring-project
RUN mvn -v
RUN mvn clean install -DskipTests
EXPOSE 8080
ADD ./target/spring-project.jar spring-project.jar
ENTRYPOINT ["java","-jar","spring-project.ja"]