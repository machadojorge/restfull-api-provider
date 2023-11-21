FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/ativityprovider-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/application.properties application.properties

EXPOSE 27273

CMD ["java", "-jar", "app.jar"]