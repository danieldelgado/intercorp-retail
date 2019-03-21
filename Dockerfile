FROM openjdk:8-jdk-alpine

MAINTAINER Danielle Delgado <danieldelgado20g@gmail.com>

ARG artifact_id
ARG artifact_version

ENV artifact ${artifact_id}-${artifact_version}.jar

WORKDIR /usr/src/app/${artifact_id}

# Install app dependencies
COPY src/main/resources/life_expectancy_birth.csv /usr/src/app/life_expectancy_birth.csv
COPY target/${artifact} /usr/src/app/${artifact_id}/${artifact}

EXPOSE 8080

CMD exec java -Duser.timezone=America/Lima -jar ${artifact}