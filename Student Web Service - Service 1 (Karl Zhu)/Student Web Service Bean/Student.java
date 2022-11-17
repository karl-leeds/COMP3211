package Students.bean;

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

    public String get_StrCourse() {
        String cour = "";
        for(int i = 0; i < course.length; i++){
            if(i == 0){
                cour += course[i];
            }else {
                cour += "@" + course[i];
            }
        }
        return cour;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public int equal(Students obj){
        //if name, student-number, and course information are equals, which means these two object are equal
        if(this.getStudent_name().equals(obj.getStudent_name())&& this.getStudent_number().equals(obj.getStudent_number())&& this.get_str_Course().equals(obj.get_str_Course())){
            return 1;//means equal
        }
        return 0;//means do not equal
    }

}
