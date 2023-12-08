FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN apk add maven

COPY target/ativityprovider-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/application.properties application.properties

COPY src/main/resources/templates templates/

EXPOSE 8080 5432

CMD ["java", "-jar", "app.jar"]