FROM openjdk:jdk-oracle

COPY target/digital-0.0.1-SNAPSHOT.jar /opt/digital.jar

CMD ["java", "-jar", "/opt/digital.jar"]

EXPOSE 8086