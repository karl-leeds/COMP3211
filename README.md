# COMP3211 Distributed Systems Coursework 2

we design a small web application for integrate client in window system for three web service based on IntelliJ IDEA 2021.3.2, JDK 1.8, jetty 9.4.45 and MySQL-8.0.20-winx64 and all jar package appeared in the git.

install:
you can easily download all file appeared in the git, and download IntelliJ IDEA 2021.3.2, JDK 1.8, jetty 9.4.45 and MySQL-8.0.20-winx64.

compile:
in order to deploy it, you should first open MYSQL database and copy the database structure file and paste to the terminal to create the database for two web services.

to run two web services, first open the IDEA for Restful web service, and put two web service source file into it(noticed that the package name should similar to the folder name appeared in the git). And find xml_jar, jersey_jar, and jdbc_jar folder, then deploy all jar file to the IDEA. Then you are be able to use JDK1.8 to run two web services. *please do not forget to put druid.properties file in to your IDEA resources and change the username and password for your own computer.

for the jsp and servlet part, first open a new IDEA and create a web project. Put the


In this report, I have designed a RESTful web service based on IntelliJ IDEA 2021.3.2, JDK 1.8 druid-1.1.19.jar, MySQL-connector-java-8.0.20.jar, junit4.jar, dom4j-2.0.3.jar, jersey jar file appeared in the lab and MySQL-8.0.20-winx64. I designed the database structure, and create a Bean class for the structure. Then, create JDBC according to the Bean class and test it using a test class. And implement get, post, put and delete in the service class. And using the HttpServer object to gain requests from the client. At last, create a client class to test whether or not the service can be used correctly.