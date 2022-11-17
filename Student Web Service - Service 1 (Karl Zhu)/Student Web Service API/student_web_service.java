package Students.API;
import Students.bean.Student;
import Students.JDBC.StudentJDBC;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//The GET Method is used to retrieve a student's information from the database based on different student parameters (Student ID and Number)
//The POST Method is used to insert a new student's information to the database
//The PUT Method is used to update a student's information in the database
//The DELETE Method is used to delete a student's information from the database based on different student parameters (Student ID and Number)

@Path("/students")
public class Student_Web_Service {

    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_XML)
    public String getStudentInfoByID(
            @QueryParam("student_id") long student_id,
    ) throws Exception {
        //Create a new Student object using the student ID. 
        Student student = new Student();
        student.setStudentID(student_id);

        //Obtain a connection from the MySQL database
        Connection connection = StudentsJDBC.getConnection();

        //Searching the database to see if the student is there
        List<Students> result = StudentsJDBC.getStudent(connection, student);

        //Data header for XML (data output format)
        String xml_data = "<?xml version=\"1.0\"?>" +
                "<students>";

        //Generating the XML output for our data result
        for(int i = 0; i < result.size(); i++){
            xml_data += "<student>";
            xml_data += "<name>" + result.get(i).getStudentName() + "</name>";
            xml_data += "<stu_ID>" + result.get(i).getStudentID() + "</stu_ID>";
            xml_data += "<number>" + result.get(i).getStudentNumber() + "</number>";
            String courseString = "";
            for(int o = 0;o < result.get(i).getCourse().length;o++){
                course_str += result.get(i).getCourse()[o];
                if(o != result.get(i).getCourse().length-1){
                    courseString += "@";
                }
            }
            xml_data += "<course>" + course_str + "</course>";
            xml_data += "</student>";
        }
        xml_data += "</students>";
        return xml_data;
    }

    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_XML)
    public String getStudentInfoByNumber(
            @QueryParam("student_number") long student_number,
    ) throws Exception {
        //Create a new Student object using the student number. 
        Student student = new Student();
        student.setStudentNumber(student_number);

        //Obtain a connection from the MySQL database
        Connection connection = StudentsJDBC.getConnection();

        //Searching the database to see if the student is there
        List<Students> result = StudentsJDBC.getStudent(connection, student);

        //Data header for XML (data output format)
        String xml_data = "<?xml version=\"1.0\"?>" +
                "<students>";

        //Generating the XML output for our data result
        for(int i = 0; i < result.size(); i++){
            xml_data += "<student>";
            xml_data += "<name>" + result.get(i).getStudentName() + "</name>";
            xml_data += "<stu_ID>" + result.get(i).getStudentID() + "</stu_ID>";
            xml_data += "<number>" + result.get(i).getStudentNumber() + "</number>";
            String courseString = "";
            for(int o = 0;o < result.get(i).getCourse().length;o++){
                course_str += result.get(i).getCourse()[o];
                if(o != result.get(i).getCourse().length-1){
                    courseString += "@";
                }
            }
            xml_data += "<course>" + course_str + "</course>";
            xml_data += "</student>";
        }
        xml_data += "</students>";
        return xml_data;
    }

    @POST
    @Path("/post")
    @Produces(MediaType.TEXT_PLAIN)
    public String postStudentInfo(
            @QueryParam("student_name") String student_name,
            @QueryParam("student_number") String student_number,
            @QueryParam("student_course") String student_course
    ) throws Exception {
        //Create a new student object from the parameters.
        Student student = new Student();

        student.setStudentName(student_name);
        student.setStudentNumber(student_number);
        String[] course = student_course.split("@");
        student.setCourse(course);

        //Obtain a connection from the MySQL database
        Connection connection = StudentsJDBC.getConnection();
        
        //Searching the database to see if the student is there
        int result = StudentsJDBC.insert(connection, student);

        //If the result is 1, then the new student has been successfully added to the database, otherwise it is not successful
        return result+"";
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudentInfo(
            @QueryParam("student_id_cur") long student_id_cur,
            @QueryParam("student_name_cur") String student_name_cur,
            @QueryParam("student_number_cur") String student_number_cur,
            @QueryParam("student_course_cur") String student_course_cur,
            @QueryParam("student_name") String student_name,
            @QueryParam("student_number") String student_number,
            @QueryParam("student_course") String student_course
    ) throws Exception {
        //Using the above parameters, we create 2 student objects.

        Student studentSearch = new Student();
        studentSearch.setStudentID(student_id_cur);
        studentSearch.setStudentName(student_name_cur);
        studentSearch.setStudentNumber(student_number_cur);
        String[] courseSearch = student_course_cur.split("@");
        studentSearch.setCourse(courSearch);

        Student student = new Student();
        student.setStudentName(student_name);
        student.setStudentNumber(student_number);
        String[] course = student_course.split("@");
        student.setCourse(course);

        //Obtain a connection from the MySQL database
        Connection connection = StudentsJDBC.getConnection();

        //Searching the database to see if the student is there
        int result = StudentsJDBC.update(connection, studentSearch, student);

        //If the result is 1, then the student's information has been successfully updated, otherwise it is not successful
        return result+"";
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteStudentByID(
            @QueryParam("student_id") long student_id,
    ) throws Exception {
        //Create a new Student object using the student ID. 
        Student student = new Student();
        student.setStudentID(student_id);

        //Obtain a connection from the MySQL database
        Connection connection = StudentsJDBC.getConnection();

        //Searching the database to see if the student is there
        int result = StudentJDBC.delete(connection, student);

        //If the result is 1, then the student has been successfully deleted from the database, otherwise it is not successful
        return result+"";
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteStudentByNumber(
            @QueryParam("student_number") long student_number,
    ) throws Exception {
        //Create a new Student object using the student ID. 
        Student student = new Student();
        student.setStudentNumber(student_number);

        //Obtain a connection from the MySQL database
        Connection connection = StudentsJDBC.getConnection();

        //Searching the database to see if the student is there
        int result = StudentJDBC.delete(connection, student);

        //If the result is 1, then the student has been successfully deleted from the database, otherwise it is not successful
        return result+"";
    }
}
