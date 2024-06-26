FROM adoptopenjdk/openjdk17:alpine-jre

WORKDIR /app

COPY build/libs/*.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/application.jar"]
