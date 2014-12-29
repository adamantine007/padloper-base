# Phone Book - data base.

# Description
This is my tutorial project.
I made it to learn how to use:
- Java SE 
- Java Servlet
- Tomcat 
- Ajax
- Gson
- Maven
# How to run
- first you must build project with Maven.
- then deploy assembled .war file on some application server.
- run your browser and go to localhost/index.html

 I prefere Apache Tomcat servlet container, 
 because very easy to configure this framework and start to work.
# What you can
 Data base have next CRUD operations: 
 - add person
 - select person
 - update information about person
 - delete person

 Comunication with DB based on Java Servlets + Ajax.

 A client have 4 section on one HTML page: add, select, delete person
 and commit DB. When client commit DB, all data convert to JSON format
 and stored in the 'data.json' file.

 Unfortunately, if client didn't commit before undeploy .war file,
 changes after last commit will not save.
# Propositions
 If you have some offer to change code,
 or to add some feature, please contact me.
