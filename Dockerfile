FROM openjdk:21

COPY control-hub-*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]