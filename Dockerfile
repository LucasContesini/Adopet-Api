FROM java:11
EXPOSE 9091
ADD /target/adopet-0.0.1-SNAPSHOT.jar adopet-0.0.1-SNAPSHOT.jar
ENTRY'POINT ["java", "-jar", "adopet-0.0.1-SNAPSHOT.jar"]