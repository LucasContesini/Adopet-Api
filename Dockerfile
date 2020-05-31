FROM java:11
EXPOSE 9090
ADD /target/adopet-0.0.1-SNAPSHOT.jar adopet-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "adopet-0.0.1-SNAPSHOT.jar"]