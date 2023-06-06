FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=./build/libs/dispatch-service-1.0.jar
COPY ${JAR_FILE} DispatchService.jar
ENTRYPOINT ["java","-jar","DispatchService.jar"]