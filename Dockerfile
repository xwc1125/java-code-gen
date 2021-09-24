FROM java:8
MAINTAINER xwc1125 <xwc1125@qq.com>
VOLUME /tmp
ADD gen/target/*.jar gen.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gen.jar"]
