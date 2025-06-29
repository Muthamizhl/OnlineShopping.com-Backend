FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/price-service-0.0.1-SNAPSHOT.jar price-service.jar
ENTRYPOINT ["java","-jar","/price-service.jar"]
