FROM openjdk:11-jdk
EXPOSE 8763
ADD /target/user-service.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]