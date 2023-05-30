FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=./build/libs/taxi-service-1.0.jar
COPY ${JAR_FILE} TaxiService.jar
ENTRYPOINT ["java","-jar","TaxiService.jar"]