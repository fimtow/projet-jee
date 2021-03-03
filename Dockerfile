FROM tomcat:9.0.41

ADD application/target/application.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]