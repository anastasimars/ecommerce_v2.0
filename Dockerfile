#FROM openjdk:21-jdk-oracle
FROM amazoncorretto:22
ADD target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "ecommerce.jar"]