FROM openjdk:17-alpine

WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=production
ADD build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]