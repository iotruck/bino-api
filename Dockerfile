FROM openjdk:8

ARG PROFILE

ENV PROFILE=${PROFILE}

WORKDIR /opt/iotruck

COPY /target/bino*.jar iotruck.jar

SHELL ["/bin/sh","-c"]

EXPOSE 8080

CMD java -jar /opt/iotruck/iotruck.jar --spring.profiles.active=${PROFILE}