# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#FROM alpine:latest
#RUN mkdir /opt/tomcat/


#FROM tomcat:9.0
#MAINTAINER Aayushi Shah (ayushidshah2000@gmail.com)

#COPY target/*.war /usr/local/tomcat/webapps/

#EXPOSE 8080
#CMD ["/opt/tomcat/bin/catalina.sh", "run"]


FROM tomcat:9.0
MAINTAINER Aayushi Shah (ayushidshah2000@gmail.com)
COPY target/credapp.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]