#Docker Build Maven Stage

FROM maven:3.9.1 AS build

# Refer to Maven build -> finalName
# cd /opt/app
WORKDIR /opt/app
COPY ./ /opt/app

RUN mvn clean install

# cp target/spring-boot-web.jar /opt/app/spring-boot-web.jar
FROM openjdk:17-alpine
COPY --from=build /opt/app/target/spring-boot-web.jar/ spring-boot-web.jar
ENV PORT 8443
EXPOSE $PORT

# java -jar /opt/app/spring-boot-web.jar
ENTRYPOINT ["java","-jar","-Dserver.port=${PORT}","spring-boot-web.jar"]

## sudo docker run -p 8080:8080 -t project-1.0.jar
## sudo docker run -p 80:8080 -t project-1.0
## sudo docker run -p 443:8443 -t project-1.0
