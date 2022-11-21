# COMP3211 Distributed Systems Coursework 2

we design a small web application to integrate the client in the window system for three web services based on IntelliJ IDEA 2021.3.2, JDK 1.8, jetty 9.4.45, and MySQL-8.0.20-winx64 and all jar packages appeared in the git.

install:
you can easily download all files that appeared in the git, and download IntelliJ IDEA 2021.3.2, JDK 1.8, jetty 9.4.45, and MySQL-8.0.20-winx64.

compile and run:
to deploy it, you should first open the MYSQL database and copy the database structure file and paste it to the terminal to create the database for two web services.

to run two web services, first open the IDEA for Restful web service, and put two web service source files into it(noticed that the package name should similar to the folder name that appeared in the git). And find xml_jar, jersey_jar, and jdbc_jar folders, then deploy all jar files to the IDEA. Then you can use JDK1.8 to run two web services. *Please do not forget to put the druid.properties file into your IDEA resources and change the username and password for your computer.

for the JSP and servlet part, first, open a new IDEA and create a web project. Put all files in the folder "Frontend Interface" to the webapp folder and put all files in the folder "servlet code" in the java folder in the IDEA. Then deploy all jar files in axis_jar, jersey_jar, and unirest_jar folders to the IDEA. For the third web service part, you should first open Url: https://pdfgeneratorapi.com/ in the browser, and sign in using account:sc20yx@leeds.ac.uk and password: Comp3211 and go into Admin Panel to click create JWT button. Then paste the JWT to the variable jwt in the get_Servlet file. 
at last, you should edit the configuration and artifact for this part.
configuration: first find the "Edit configuration" button at the left side of the run button. and change the URL to "http://localhost:8080/serve", and click the "Deployment" button at the right of the "Server" button, and change the "Use custom context root" to "/serve".
artifact: first find the "Project Structure" button and go to the "Artifacts" button, then you can be noticed that there is a war exploded element in the middle of the page, at last, you should double-click all elements in the "Available Elements" list.

after you complete all deployments, you can use the run button to run two web services and the web application.