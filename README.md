# Recipes 
This application allows users to manage their favourite recipes. 

Users are able to add, update, remove and fetch recipes and additionally filter available recipes based on criterias.


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
For building and running the application you need:
- [OpenJDK 11](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)
- [Maven 3.6](https://maven.apache.org)
- Mysql Server

Or simply run the application with Docker.

## Run the Application Locally
Run the application on terminal as follows:
```
java -Dserver.port=9090 -jar target/recipes-0.0.1-SNAPSHOT.jar
```

Or run the application with your preferred IDE, port must be added in VM options:
```
-Dserver.port=9090
```

Local profile will be used if you run the application without Docker.

``
spring.profiles.active=local
``

## Run the Application With Docker
Start in detached mode:
```
docker-compose up -d
```

Follow the application log:
```
docker logs -f recipes_api_service_1
```

Stop:
```
docker-compose down
```

## Swagger UI
Swagger UI is accessible at http://localhost:9090/swagger-ui/index.html#/

## Health Check and Metrics
Application uses Spring actuator for health check and metrics.

Metrics: http://localhost:9091/actuator/metrics

Health: http://localhost:9091/actuator/health
