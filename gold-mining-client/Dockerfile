FROM openjdk:17-alpine
COPY build/libs/gold-mining-client-1.0.0.jar /client.jar
CMD sleep 10
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/client.jar"]
