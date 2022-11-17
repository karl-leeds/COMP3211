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
        <h1 style="color: #c8d8e4;">DELETE Method - Delete Student/Course based on ID or Name</h1>
    </div>

    <form action = ".jsp" method = "DELETE" class="video">
        Student ID: <br>
        <input type = "text" name = "student_id">
        <br>
        Student Number: <br>
        <input type = "text" name = "student_number">
        <br>
        Course ID: <br>
        <input type = "text" name = "course_id" />
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