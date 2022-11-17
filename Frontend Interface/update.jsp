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
        <h1 style="color: #c8d8e4;">PUT Method - Update Student Information/Course Information</h1>
    </div>

    <form action = ".jsp" method = "PUT" class="video">
        Student Number: <br>
        <input type = "text" name = "student_number">
        <br>
        Student Name: <br>
        <input type = "text" name = "student_name">
        <br>
        Student Course: <br>
        <input type = "text" name = "student_course">
        <br>
        Course Name: <br>
        <input type = "text" name = "course_name" />
        <br>
        <input type = "submit" value = "Submit" />
     </form>
    
    
</body>

<footer>
    <h3> COMP3211 Coursework 2 - Karl Zhu, Yuxiang Xiao </h3>
</footer>

</html>