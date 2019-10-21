FROM openjdk:11
ADD target/MuzixApplication-0.0.1-SNAPSHOT.jar muzixapp.jar
EXPOSE 8094
ENTRYPOINT ["java","-jar","muzixapp.jar"]