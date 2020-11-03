FROM openjdk:8-jre
EXPOSE 8080:8080
ADD /target/limits-service-0.0.1-SNAPSHOT.jar limits-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "limits-service-0.0.1-SNAPSHOT.jar"]