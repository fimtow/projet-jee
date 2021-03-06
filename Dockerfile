FROM tomcat:9.0-alpine

ADD application/target/application.war /usr/local/tomcat/webapps/
ADD server.xml /usr/local/tomcat/conf/

EXPOSE 80
EXPOSE 443
CMD ["catalina.sh", "run"]