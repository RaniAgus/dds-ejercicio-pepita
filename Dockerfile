FROM maven:3.8.6-openjdk-8 as builder

WORKDIR /app

COPY pom.xml ./

RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]

COPY src ./src

RUN mvn package


FROM openjdk:8-jre-alpine as cron

ADD crontab /etc/cron.d/cronjob

RUN chmod 0644 /etc/cron.d/cronjob

RUN crontab /etc/cron.d/cronjob

WORKDIR /app

COPY --from=builder /app/target/*-jar-with-dependencies.jar ./application.jar

ENTRYPOINT ["crond", "-f"]


FROM openjdk:8-jre-alpine as java

WORKDIR /app

COPY --from=builder /app/target/*-jar-with-dependencies.jar ./application.jar

ENTRYPOINT [ "java", "-jar", "application.jar" ]
