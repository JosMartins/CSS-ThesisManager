FROM docker.io/maven:3-eclipse-temurin-17 AS builder
ADD pom.xml .
ADD thesisman-client/pom.xml thesisman-client/
ADD thesisman-server/pom.xml thesisman-server/
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package

FROM docker.io/eclipse-temurin:17
VOLUME /tmp
EXPOSE 8082
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
COPY --from=builder thesisman-server/target/thesisman-server-1.0-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]
