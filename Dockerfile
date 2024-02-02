FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/sermaluc-0.0.1-SNAPSHOT.jar .
EXPOSE 8089
ENTRYPOINT ["java","-jar","sermaluc-0.0.1-SNAPSHOT.jar"]