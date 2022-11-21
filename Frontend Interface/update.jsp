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
        <h1 style="color: #c8d8e4;">PUT Method - Update Student Information/Event Information</h1>
    </div>


    <%
        String s_stu1 = (String) request.getParameter("Locat_stu_id");
        String s_stu2 = (String) request.getParameter("Locat_stu_name");
        String s_stu3 = (String) request.getParameter("Locat_stu_number");
        String s_stu4 = (String) request.getParameter("Locat_stu_course");

        String stu2 = (String) request.getParameter("stu_name");
        String stu3 = (String) request.getParameter("stu_number");
        String stu4 = (String) request.getParameter("stu_course");

        String s_eve = (String) request.getParameter("Locat_eve_title");
        String s_eve5 = (String) request.getParameter("Locat_user_name");
        String s_eve2 = (String) request.getParameter("Locat_eve_start_time");
        String s_eve3 = (String) request.getParameter("Locat_eve_end_time");
        String s_eve4 = (String) request.getParameter("Locat_eve_id");

        String eve = (String) request.getParameter("eve_title");
        String eve5 = (String) request.getParameter("user_name");
        String eve1 = (String) request.getParameter("eve_description");
        String eve2 = (String) request.getParameter("eve_start_time");
        String eve3 = (String) request.getParameter("eve_end_time");

        if(stu2 == null) stu2 = "";
        if(stu3 == null) stu3 = "";
        if(stu4 == null) stu4 = "";

        if(eve == null) eve = "";
        if(eve2 == null) eve2 = "";
        if(eve3 == null) eve3 = "";
        if(eve5 == null) eve5 = "";
        if(eve1 == null) eve1 = "";

        if(s_stu1 == null) s_stu1 = "";
        if(s_stu2 == null) s_stu2 = "";
        if(s_stu3 == null) s_stu3 = "";
        if(s_stu4 == null) s_stu4 = "";

        if(s_eve == null) s_eve = "";
        if(s_eve2 == null) s_eve2 = "";
        if(s_eve3 == null) s_eve3 = "";
        if(s_eve4 == null) s_eve4 = "";
        if(s_eve5 == null) s_eve5 = "";


        String a = (String) request.getSession().getAttribute("error");
        if(a != "0" && a != null){
            a = "error occured";
        }else{
            a = "";
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


    %>

    <form action ="/serve/put_Servlet" method ="post" class="video">
        <%=a%><br>


        Student Information Update: Student that is to be updated:<br>
        <br>
        Student ID: <input type ="text" value="<%=s_stu1%>" oninput="value=value.replace(/[^\d]/g,'')" name ="Locat_stu_id"/>
        <br>
        Student Name: <input type ="text" value="<%=s_stu2%>" name ="Locat_stu_name" />
        <br>
        Student Number:<input type ="text" value="<%=s_stu3%>" name ="Locat_stu_number"/>
        <br>
        Course Name: <input type ="text" value="<%=s_stu4%>" name ="Locat_stu_course" />
        <br><br>
        <br>

        Student Information - To update student with:<br>
        <br>
        Student Name: <input type ="text" value="<%=stu2%>" name ="stu_name" />
        <br>
        Student Number:<input type ="text" value="<%=stu3%>" name ="stu_number"/>
        <br>
        Course Name: <input type ="text" value="<%=stu4%>" name ="stu_course" />
        <br><br><br>



        Event Information Update: Event that is to be updated:<br>
        <br>
        Event ID: <input type ="text" value="<%=s_eve4%>" oninput="value=value.replace(/[^\d]/g,'')" name ="Locat_eve_id"/>
        <br>
        Event Title: <input type ="text" value="<%=s_eve%>" name ="Locat_eve_title" />
        <br>
        Event User Name: <input type ="text" value="<%=s_eve5%>" name ="Locat_user_name" />
        <br>
        Event Start Time:<input type ="text" value="<%=s_eve2%>" name ="Locat_eve_start_time"/>
        <br>
        Event End Time: <input type ="text" value="<%=s_eve3%>" name ="Locat_eve_end_time"/>
        <br><br>
        <br>
        Event Information: To update event with:<br>
        <br>
        Event Title: <input type ="text" value="<%=eve%>" name ="eve_title" />
        <br>
        Event User Name: <input type ="text" value="<%=eve5%>" name ="user_name" />
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