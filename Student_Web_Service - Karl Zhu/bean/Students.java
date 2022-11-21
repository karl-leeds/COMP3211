package Student_Web_Service.bean;

public class Students {
    private long studentID;
    private String studentNumber;
    private String studentName;
    private String[] course;

    public Students() {
        this.studentID = -1;
        this.studentNumber = "null";
        this.studentName = "null";
        this.course = null;
    }

    public Students(long studentID, String studentNumber, String studentName, String[] course) {
        this.studentID = studentID;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.course = course;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String[] getCourse() {
        return course;
    }

    public String get_str_Course() {
        String cour = "";
        if(course != null) {
            for (int i = 0; i < course.length; i++) {
                if (i == 0) {
                    cour += course[i];
                } else {
                    cour += "@" + course[i];
                }
            }
        }
        if(cour == ""){
            cour = "null";
        }
        return cour;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public int equal(Students student){
        //if the names, student-numbers, and course information are equal, then these two student objects are equal
        if(this.getStudentName().equals(student.getStudentName())&& this.getStudentNumber().equals(student.getStudentNumber())&& this.get_str_Course().equals(student.get_str_Course())){
            return 1;//The 2 student objects are equal
        }
        return 0;//The 2 student objects are not equal
    }

}
