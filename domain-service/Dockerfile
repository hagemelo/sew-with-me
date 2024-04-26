# the first stage of our build will use a maven 3.6.1 parent image
FROM maven:3.6.3-openjdk-17 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./
 
# package our application codedocker 
RUN mvn clean package
RUN pwd

FROM openjdk:17

WORKDIR /usr/src/app  


# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD target/domain-1.0.0.jar ./

EXPOSE 8080

CMD ["java", "-jar", "-XX:+UseG1GC", "domain-1.0.0.jar"]