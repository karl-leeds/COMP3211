package Student_Web_Service.API;
import Student_Web_Service.bean.Students;
import Student_Web_Service.JDBC.StudentWebServiceJDBC;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.util.List;

//The GET Method is used to retrieve a student's information from the database based on different student parameters (Student ID and Number)
//The POST Method is used to insertStudent a new student's information to the database
//The PUT Method is used to update a student's information in the database
//The DELETE Method is used to delete a student's information from the database based on different student parameters (Student ID and Number)

@Path("/students")
public class StudentWebService {

    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_XML)
    public String getStudent_info(
            @QueryParam("student_id") long student_id,
            @QueryParam("student_name") String student_name,
            @QueryParam("student_number") String student_number,
            @QueryParam("student_course") String student_course
    ) throws Exception {
        Connection conn = null;
        try {

            //Create a new Student object using the above parameters.
            Students newStudent = new Students();
            newStudent.setStudentID(student_id);
            newStudent.setStudentName(student_name);
            newStudent.setStudentNumber(student_number);
            String[] course = student_course.split("@");
            newStudent.setCourse(course);


            //Obtain a connection from the MySQL database
            conn = StudentWebServiceJDBC.getConnection();

            //Searching the database to see if the student is there
            List<Students> result = StudentWebServiceJDBC.retrieve(conn, newStudent);

            //Data header for XML (data output format)
            String xmlData = "<?xml version=\"1.0\"?>" +
                    "<students>";

            //Generating the XML output for our data result
            for (int i = 0; i < result.size(); i++) {
                xmlData += "<student>";
                xmlData += "<name>" + result.get(i).getStudentName() + "</name>";
                xmlData += "<stu_ID>" + result.get(i).getStudentID() + "</stu_ID>";
                xmlData += "<number>" + result.get(i).getStudentNumber() + "</number>";
                String courseString = "";
                for (int o = 0; o < result.get(i).getCourse().length; o++) {
                    courseString += result.get(i).getCourse()[o];
                    if (o != result.get(i).getCourse().length - 1) {
                        courseString += "@";
                    }
                }
                xmlData += "<course>" + courseString + "</course>";
                xmlData += "</student>";
            }
            xmlData += "</students>";

            return xmlData;

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            StudentWebServiceJDBC.close(conn);

        }
        return "";
    }

    @POST
    @Path("/post")
    @Produces(MediaType.TEXT_PLAIN)
    public String postStudent_info(
            @QueryParam("student_name") String student_name,
            @QueryParam("student_number") String student_number,
            @QueryParam("student_course") String student_course
    ) throws Exception {
        Connection conn = null;
        try {

            //Create a new student object from the parameters.
            Students newStudent = new Students();
            newStudent.setStudentName(student_name);
            newStudent.setStudentNumber(student_number);
            String[] course = student_course.split("@");
            newStudent.setCourse(course);
            //Obtain a connection from the MySQL database
            conn = StudentWebServiceJDBC.getConnection();
            //Searching the database to see if the student is there
            int result = StudentWebServiceJDBC.insert(conn, newStudent);
            //If the result is 1, then the new student has been successfully added to the database, otherwise it is not successful

            return result+"";

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            StudentWebServiceJDBC.close(conn);

        }
        return "0";


    }

    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudent_info(
            @QueryParam("student_id_s") long student_id_s,
            @QueryParam("student_name_s") String student_name_s,
            @QueryParam("student_number_s") String student_number_s,
            @QueryParam("student_course_s") String student_course_s,

            @QueryParam("student_name") String student_name,
            @QueryParam("student_number") String student_number,
            @QueryParam("student_course") String student_course
    ) throws Exception {
        Connection conn = null;

        try {
            //Using the above parameters, we create 2 student objects.

            Students studentSearchObject = new Students();
            studentSearchObject.setStudentID(student_id_s);
            studentSearchObject.setStudentName(student_name_s);
            studentSearchObject.setStudentNumber(student_number_s);
            String[] courseSearchObject = student_course_s.split("@");
            studentSearchObject.setCourse(courseSearchObject);

            Students updateStudentObject = new Students();
            updateStudentObject.setStudentName(student_name);
            updateStudentObject.setStudentNumber(student_number);
            String[] updateCourseObject = student_course.split("@");
            updateStudentObject.setCourse(updateCourseObject);

            //Obtain a connection from the MySQL database
            conn = StudentWebServiceJDBC.getConnection();

            //Searching the database to see if the student is there
            int result = StudentWebServiceJDBC.update(conn, studentSearchObject, updateStudentObject);
            //If the result is 1, then the student's information has been successfully updated, otherwise it is not successful

            return result+"";

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            StudentWebServiceJDBC.close(conn);
        }
        return "0";

    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteStudent_info(
            @QueryParam("student_id") long student_id,
            @QueryParam("student_name") String student_name,
            @QueryParam("student_number") String student_number,
            @QueryParam("student_course") String student_course
    ) throws Exception {
        Connection conn = null;

        try {
            //Create a new Student object using the above parameters.
            Students studentObject = new Students();
            studentObject.setStudentID(student_id);
            studentObject.setStudentName(student_name);
            studentObject.setStudentNumber(student_number);
            String[] course = student_course.split("@");
            studentObject.setCourse(course);

            //Obtain a connection from the MySQL database
            conn = StudentWebServiceJDBC.getConnection();

            //Searching the database to see if the student is there
            int result = StudentWebServiceJDBC.delete(conn, studentObject);

            //If the result is 1, then the student has been successfully deleted from the database, otherwise it is not successful
            return result+"";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            StudentWebServiceJDBC.close(conn);
        }
        return "0";
    }
}
