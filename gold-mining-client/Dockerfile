FROM openjdk:17-alpine
COPY build/libs/gold-mining-client-1.0.0.jar /api.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/api.jar"]
