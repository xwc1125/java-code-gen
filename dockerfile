FROM java:8
VOLUME /tmp
ADD gen/target/*.jar gen.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gen.jar"]