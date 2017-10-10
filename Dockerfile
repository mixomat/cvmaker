FROM openjdk:8-jdk-alpine

RUN mkdir /app
WORKDIR '/app'

COPY  target/cvmaker-*-SNAPSHOT.jar /app/cvmaker.jar

WORKDIR "/app"
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "cvmaker.jar"]
