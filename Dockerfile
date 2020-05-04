FROM openjdk:8
ADD target/bloggy.jar bloggy.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/bloggy.jar"]