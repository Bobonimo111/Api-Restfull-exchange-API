FROM amazoncorretto:21-alpine

WORKDIR /app

COPY target/apiRestFull-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/application.properties application.properties

# VOLUME [ "/config" ]

EXPOSE 8080

ENTRYPOINT [ "java","-jar","/app/app.jar","--spring.config.location=file:/app/application.properties" ]