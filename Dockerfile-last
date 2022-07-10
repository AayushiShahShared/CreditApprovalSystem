
FROM openjdk:11
MAINTAINER Aayushi Shah (ayushidshah2000@gmail.com)
RUN mkdir /opt/tomcat/

#RUN yum install unzip
WORKDIR /opt/tomcat
COPY apache-tomcat-9.0.59.tar.gz .
RUN tar -xvzf apache-tomcat-9.0.59.tar.gz
RUN mv apache-tomcat-9.0.59/* /opt/tomcat/.

RUN apt-get update
RUN apt-get install -y maven


COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
WORKDIR /usr/local/service
RUN mvn package

COPY target/credapp.war /opt/tomcat/webapps/
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]


#FROM tomcat:9.0
#COPY target/credapp.war /usr/local/tomcat/webapps/
