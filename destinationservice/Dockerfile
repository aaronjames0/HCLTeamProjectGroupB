FROM openjdk:11-jdk
EXPOSE 8080
ADD /target/destinationservice.jar destinationservice.jar
ENTRYPOINT ["java","-jar","destinationservice.jar"]