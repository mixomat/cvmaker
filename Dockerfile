FROM java:8-jre

VOLUME /tmp
ADD target/cvmaker-0.0.1-SNAPSHOT.jar cvmaker.jar

RUN bash -c 'touch /cvmaker.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","cvmaker.jar"]
