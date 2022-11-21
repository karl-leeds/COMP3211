# COMP3211 Distributed Systems Coursework 2

# Yuxiang Xiao, Karl Zhu

For the COMP3211 Coursework 2, we designed and implemented an integrated web services application that encompasses three web services, a student directory service, a course directory service that is corresponded to each student, and an external PDF service. 

Our web application is based on the Windows system, and our dependencies include: IntelliJ IDEA 2021.3.2, JDK 1.8, jetty 9.4.45, and MySQL-8.0.20-winx64 and all of the jar packages in our git repository.

Installing dependencies:
* Download/clone our git repository

* Download IntelliJ IDEA 2021.3.2, JDK 1.8, jetty 9.4.45, and MySQL-8.0.20-winx64.

Compilation and Execution:
* Open the MySQL database, and copy + paste the database structure file to the MySQL terminal interface to create the database for our two self-created web services.

* Now, open IntelliJ IDEA, and put the two self-created web service source files into a new project (Make sure that the package name is the same as the folder names in the git repository).

* Navigate to the xml_jar, jersey_jar, jdbc_jar directories, and add all of the jar files present to IntelliJ as libraries to the modules section of the project structure tab (File->Project Structure->Libraries).

* Please do not forget to put the druid.properties file into your resources folder in the IntelliJ project, and change the username and password according to your own MySQL server.

* Now, make sure you are using JDK 1.8 in order to run our web services.


For our JSP and servlet part, open a new IntelliJ web application project. 

* Put all of the files in the Frontend Interface folder to the webapp folder and put all of the files in the Servlet Code folder to the Java folder of the IntelliJ project. 

* Navigate to the axis_jar, jersey_jar, and unirest_jar directories, and add all of the jar files present to IntelliJ as libraries to the modules section of the project structure tab (File->Project Structure->Libraries).

For the external web service, navigate to https://pdfgeneratorapi.com/ in any browser, and sign in using the following credentials:
* account:sc20yx@leeds.ac.uk
* password: Comp3211 

Now, go into the Admin Panel and click "Create JWT". Paste the JWT to the variable "jwt" in the get_Servlet file. 

Finally, edit the configuration and artifact.
* Configuration: Click the "Edit Configuration" button, located at the left side of the run button in IntelliJ, and change the URL to "http://localhost:8080/serve" and click the "Deployment" button at the right of the "Server" button, and change "Use custom context root" to "/serve".

* Artifact: Click the "Project Structure" button and go to the "Artifacts" view, there you will see that there is a war exploded element in the middle of the page. Double-click all elements in the "Available Elements" list.

After you complete all of the above steps, you can use the run button to run the two web services and the integrated web application.
