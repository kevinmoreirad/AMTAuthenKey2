FROM jboss/wildfly:10.1.0.Final

ADD src/AMTAuthenKey/target/AMTProjectBootcamp-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/AMTProjectBootcamp-1.0-SNAPSHOT.war
ADD images/wildfly/data/mysql-connector-java-5.1.44-bin.jar /opt/jboss/wildfly/standalone/deployments/mysql-connector-java-5.1.44-bin.jar
ADD images/wildfly/data/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

