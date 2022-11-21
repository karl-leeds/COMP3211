package Student_Web_Service.bean;

public class Student {
    private long student_id;
    private String student_number;
    private String student_name;
    private String[] course;

    public Student() {
        this.student_id = -1;
        this.student_number = null;
        this.student_name = null;
        this.course = null;
    }

    public Student(long student_id, String student_number, String student_name, String[] course) {
        this.student_id = student_id;
        this.student_number = student_number;
        this.student_name = student_name;
        this.course = course;
    }

    public long getStudentID() {
        return student_id;
    }

    public void setStudentID(long student_id) {
        this.student_id = student_id;
    }

    public String getStudentNumber() {
        return student_number;
    }

    public void setStudentNumber(String student_number) {
        this.student_number = student_number;
    }

    public String getStudentName() {
        return student_name;
    }

    public void setStudentName(String student_name) {
        this.student_name = student_name;
    }

    public String[] getCourse() {
        return course;
    }

    public String getCourseString() {
        String course = "";
        for(int i = 0; i < this.course.length; i++){
            if(i == 0){
                course += this.course[i];
            }else {
                course += "@" + this.course[i];
            }
        }
        return course;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public int equal(Student student){
        //if the names, student-numbers, and course information are equal, then these two student objects are equal
        if(this.getStudentName().equals(student.getStudentName())&& this.getStudentNumber().equals(student.getStudentNumber())&& this.getCourseString().equals(student.getCourseString())){
            return 1;//The 2 student objects are equal
        }
        return 0;//The 2 student objects are not equal
    }

}
