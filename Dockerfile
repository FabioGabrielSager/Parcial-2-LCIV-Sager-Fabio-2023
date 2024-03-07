FROM openjdk:17
COPY target/parcial-2-elecciones-2023-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]