FROM adoptopenjdk/openjdk11:latest
LABEL maintainer="cemcetin92@gmail.com"
VOLUME /main-app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9090
ENTRYPOINT ["java","-Dserver.port=9090","-Dspring.profiles.active=docker","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]