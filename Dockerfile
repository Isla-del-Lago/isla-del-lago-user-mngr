FROM amazoncorretto:11

EXPOSE 9000

WORKDIR /app

COPY ./target/usermanager-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "usermanager-0.0.1-SNAPSHOT.jar"]