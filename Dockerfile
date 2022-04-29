FROM openjdk:17-jdk
VOLUME /tmp
COPY target/*.jar library.jar
ENTRYPOINT ["java","-jar","/library.jar"]