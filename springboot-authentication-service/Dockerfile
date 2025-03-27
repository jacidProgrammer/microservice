FROM openjdk:8
VOLUME /tmp
EXPOSE 9100
ADD ./target/springboot-authentication-service-0.0.1-SNAPSHOT.jar authentication-service.jar
ENTRYPOINT ["java", "-jar", "/authentication-service.jar"]