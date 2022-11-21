<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="stylesheet" href="style.css" />
    <title>COMP3211 Student Timetable Web Service Application</title>
</head>

<body style="background-color: #2b6777;">
    <nav>
        <div class="logo">
            <img src="images/logo.JPG" width="300" />
        </div>
        <ul class="nav-links">
            <li><a href="home.jsp">Home</a></li>
            <li><a href="create.jsp">POST-Create</a></li>
            <li><a href="retrieve.jsp">GET-Retrieve</a></li>
            <li><a href="update.jsp">PUT-Update</a></li>
            <li><a href="delete.jsp">DELETE</a></li>
        </ul>
        <div class="menu">
            <div class="1"></div>
            <div class="2"></div>
            <div class="3"></div>
            <div class="4"></div>
            <div class="5"></div>
        </div>
    </nav>

    <div class="header">
        <h1 style="color: #c8d8e4;">POST Method - Create New Student/New Event</h1>
    </div>


    <%

        String stu1 = (String) request.getParameter("stu_id");
        String stu2 = (String) request.getParameter("stu_name");
        String stu3 = (String) request.getParameter("stu_number");
        String stu4 = (String) request.getParameter("stu_course");

        String eve = (String) request.getParameter("eve_title");
        String eve1 = (String) request.getParameter("eve_description");
        String eve2 = (String) request.getParameter("eve_start_time");
        String eve3 = (String) request.getParameter("eve_end_time");
        String eve4 = (String) request.getParameter("eve_id");


        if(stu1 == null) stu1 = "";
        if(stu2 == null) stu2 = "";
        if(stu3 == null) stu3 = "";
        if(stu4 == null) stu4 = "";

        if(eve == null) eve = "";
        if(eve2 == null) eve2 = "";
        if(eve3 == null) eve3 = "";
        if(eve4 == null) eve4 = "";
        if(eve1 == null) eve1 = "";
        

        String a = (String) request.getSession().getAttribute("error");
        
        if(a != "0" && a != null){
            eve1 = "there are some error occured";
        }
        if(a == null){
            eve1 = "";
        }
        request.getSession().removeAttribute("error");

        request.removeAttribute("stu_id");
        request.removeAttribute("stu_name");
        request.removeAttribute("stu_number");
        request.removeAttribute("stu_course");
        request.removeAttribute("eve_title");
        request.removeAttribute("eve_start_time");
        request.removeAttribute("eve_end_time");
        request.removeAttribute("eve_id");
        request.removeAttribute("eve_description");


    %>

    <form action ="/serve/post_Servlet" method ="post" class="video">
        Student Part:<br>
        <br>
        Student ID: <input type ="text" value="<%=stu1%>" oninput="value=value.replace(/[^\d]/g,'')" name ="stu_id"/>
        <br>
        Student Name: <input type ="text" value="<%=stu2%>" name ="stu_name" />
        <br>
        Student Number:<input type ="text" value="<%=stu3%>" name ="stu_number"/>
        <br>
        Course Name: <input type ="text" value="<%=stu4%>" name ="stu_course" />
        <br><br>

        Event Part:<br>
        <br>
        Event ID: <input type ="text" value="<%=eve4%>"oninput="value=value.replace(/[^\d]/g,'')"  name ="eve_id"/>
        <br>
        Event Title: <input type ="text" value="<%=eve%>" name ="eve_title" />
        <br>
        Event Start Time:<input type ="text" value="<%=eve2%>" name ="eve_start_time"/>
        <br>
        Event End Time: <input type ="text" value="<%=eve3%>" name ="eve_end_time"/>
        <br>
        Event Description: <textarea type ="text" value="<%=eve1%>" name ="eve_description"> </textarea>
        <br>
        <br><br>

        <input type ="submit" value ="Submit" />

     </form>
    
    
</body>

<footer>
    <h3> COMP3211 Coursework 2 - Karl Zhu, Yuxiang Xiao </h3>
</footer>

</html>