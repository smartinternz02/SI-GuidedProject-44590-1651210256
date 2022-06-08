FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} news-app-docker.jar
ENTRYPOINT ["java","-jar","/news-app-docker.jar"]