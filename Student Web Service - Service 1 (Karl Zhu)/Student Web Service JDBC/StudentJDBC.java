package Students.JDBC;
import Students.bean.Student;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudentJDBC{

    private static DataSource ds;
    static{
        try{
            Properties properties = new Properties();
            properties.load(StudentJDBC.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Connection connection) throws SQLException {
        if(connection != null) {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement statement) throws SQLException {
        if(statement != null) {
            try {
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet result) throws SQLException {
        if(result != null) {
            try {
                result.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static List<Students> getStudent(Connection connection, Student student) throws SQLException {
        //Initializing and setup
        List<Students> result_list = new ArrayList<Students>();
        Statement statement = null;
        ResultSet result = null;

        //Generating the SQL query string
        String sql = "select * from `students`";
        String[] temp = {"","","",""};
        if(student.getStudentName() != null){
            temp[0] = " `student_name` = '" + student.getStudentName() + "'";
        }
        if(student.getStudentNumber() != null){
            if(temp[0].equals("")) {
                temp[1] = " `student_number` = '" + student.getStudentNumber() + "'";
            }else{
                temp[1] = " and `student_number` = '" + student.getStudentNumber() + "'";
            }
        }
        if(student.getStudentID() != -1){
            if(temp[0].equals("") && temp[1].equals("")) {
                temp[2] = " `student_id` = '" + student.getStudentID() + "'";
            }else{
                temp[2] = " and `student_id` = '" + student.getStudentID() + "'";
            }
        }

        if(temp[0].equals("") || temp[1].equals("") || temp[2].equals("")) {
            if(!temp[0].equals("")){
                temp[3] = temp[3] + temp[0];
            }
            if(!temp[1].equals("")){
                temp[3] = temp[3] + temp[1];
            }
            if(!temp[2].equals("")){
                temp[3] = temp[3] + temp[2];
            }
        }

        if(!temp[3].equals("")){
            sql = sql + " where" + temp[3] + ";";
        }else{
            sql = sql + ";";
        }


        try {
            //Using the SQL query string to query data from the database
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            //Initializing and setup
            Student studentResult;
            int mark = 0;
            int count = 0;

            //Populate the studentResult object with queried data, and add it to a list
            while(result.next()){
                long id = result.getLong("student_id");
                String name = result.getString("student_name");
                String student_number = result.getString("student_number");
                String course = result.getString("student_course");

                String[] course_list = course.split("@");
                if(student.getCourse() != null){
                    String[] course = student.getCourse();
                    for(String x: course){
                        for(String s: course_list){
                            if(s.equals(x)){
                                mark = 1;
                            }
                        }
                        if(mark == 0){
                            break;
                        }else{
                            count += 1;
                            mark = 0;
                        }
                    }
                    if(count != course.length){
                        continue;
                    }
                }

                studentResult = new Student();
                studentResult.setStudentID(id);
                studentResult.setStudentName(name);
                studentResult.setStudentNumber(student_number);
                studentResult.setCourse(course_list);

                result_list.add(studentResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(statement);
            close(result);
        }
        return result_list;
    }

    public static int deleteStudent(Connection connection, Student student) throws Exception {
        //Generating the SQL query string
        String sql = "DELETE FROM `students` where ";
        //Search the database to check whether the student object data is there or not
        List<Students> result_list = getStudent(connection, student);
        if(result_list.size() == 0){
            return 0;
        }
        Statement as = null;


        try{
            //Using the generated SQL query string to delete the student object from the database
            statement = connection.createStatement();
            for(int i = 0; i < result_list.size(); i++){
                as.executeUpdate(sql + "`student_id` = " + result_list.get(i).getStudentID() + ";");
            }

            return 1;//Successful delete
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(statement);
        }
        return 0;//Unsuccessful delete
    }

    public static int insertStudent(Connection connection, Student student) throws Exception {
        //Initializing and setup
        Statement statement = null;
        //Search database to see if the student object is already in the database or not
        if(student.getStudent_number() != null){
            Student newStudent = new Student();
            newStudent.setStudentNumber(student.getStudentNumber());
            List<Students> search_result = getStudent(connection, newStudent);
            if(search_result.size() != 0){
                throw new Exception("Multiple students cannot have the same student number");
            }
        }

        //Generating the SQL query string to insert a new student
        String sql = "INSERT INTO `students`(`student_number`,`student_name`,`student_course`) values (\""
                +student.getStudentNumber() + "\",\"" + student.getStudentName()+"\",\"";

        String course = "";
        for(int i = 0; i < student.getCourse().length; i++){
            if(i == student.getCourse().length-1){
                course = course + student.getCourse()[i];
            }else {
                course = course + student.getCourse()[i] + "@";
            }
        }
        sql = sql + course + "\");";

        try{
            //Using the SQL query string to insert the new student object.
            statement = connection.createStatement();;

            statement.executeUpdate(sql);

            return 1;//Successful insert
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(statement);
        }
        return 0;//Unsuccessfull insert
    }

    public static int updateStudent(Connection connection, Student searchStudent, Student updateStudent) throws Exception {
        //Initializing and setup
        Statement statement = null;

        //Searching through the database to see if the searchStudent object data is there or not already
        List<Students> search_result = getStudent(connection, searchStudent);

        //Generating the SQL query string to update a current student
        String temp[] = {"","","",""};

        if(updateStudent.getStudentName() != null){
            temp[0] = " `student_name` = '" + updateStudent.getStudentName() + "'";
        }
        if(updateStudent.getStudentNumber() != null){
            if(temp[0].equals("")) {
                temp[1] = " `student_number` = '" + updateStudent.getStudentNumber() + "'";
            }else{
                temp[1] = " , `student_number` = '" + updateStudent.getStudentNumber() + "'";
            }
        }
        if(updateStudent.getCourse() != null){
            String course = "";
            for(int i = 0; i < updateStudent.getCourse().length; i++){
                if(i == updateStudent.getCourse().length-1){
                    course = course + updateStudent.getCourse()[i];
                }else {
                    course = course + updateStudent.getCourse()[i] + "@";
                }
            }
            if(temp[0].equals("") && temp[1].equals("")) {
                temp[2] = " `student_course` = '" + course + "'";
            }else{
                temp[2] = " , `student_course` = '" + course + "'";
            }
        }

        if(!temp[0].equals("") || !temp[1].equals("") || !temp[2].equals("")) {
            if(!temp[0].equals("")){
                temp[3] = temp[3] + temp[0];
            }
            if(!temp[1].equals("")){
                temp[3] = temp[3] + temp[1];
            }
            if(!temp[2].equals("")){
                temp[3] = temp[3] + temp[2];
            }
        }
        if(temp[3] == ""){
            throw new Exception("Please using correct student information as the parameters when updating a current student's information");
        }
        try{
            //Using the update SQL query string to update current students' information.
            statement = connection.createStatement();
            String sql = "UPDATE `students` SET " + temp[3] + " where ";
            for(int i = 0; i < search_result.size(); i++){
                statement.executeUpdate(sql + "`student_id` =" +search_result.getStudent(i).getStudentID()+";");
            }

            return 1;//Sucessful update
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(statement);
        }
        return 0;//Unsucessful update
    }

}
