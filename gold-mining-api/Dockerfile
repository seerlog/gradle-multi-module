FROM openjdk:17-alpine
COPY build/libs/gold-mining-api-1.0.0.jar /api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/api.jar"]
