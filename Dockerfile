FROM maven:3.6-jdk-8 as maven

WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src

RUN mvn package && cp target/bloggy*.jar app.jar


FROM openjdk:8-jre-alpine
LABEL maintainer="khalidit04@gmail.com"
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar
#
#ARG JAR_FILE=target/bloggy.jar
#
#ADD ${JAR_FILE} bloggy.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]