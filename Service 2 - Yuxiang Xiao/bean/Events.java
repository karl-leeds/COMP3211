package Events.bean;


public class Events {


    private long event_id;
    private String event_title;
    private String event_description;
    private String user_name;
    private String start_time;
    private String end_time;

    public Events(long event_id, String event_title, String event_description, String user_name, String start_time, String end_time) {
        this.event_id = event_id;
        this.event_title = event_title;
        this.event_description = event_description;
        this.user_name = user_name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Events() {
        this.event_id = -1;
        this.event_title = "null";
        this.event_description = "null";
        this.user_name = "null";
        this.start_time = "null";
        this.end_time = "null";
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) throws Exception {
        //to check whether the string is calander format

        String rexp = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";

        if(start_time.matches(rexp)){
            this.start_time = start_time;

        }else{
            throw new Exception("cannot set this time format");
        }
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) throws Exception {
        //to check whether the string is calander format
        String rexp = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";

        if(end_time.matches(rexp)){
            this.end_time = end_time;

        }else{
            throw new Exception("cannot set this time format");
        }
    }

    public int equal(Events obj){
        //if title, description, start time, end time and user information are equals, which means these two object are equal
        if(this.getEvent_title().equals(obj.getEvent_title()) && this.getStart_time().equals(obj.getStart_time()) && this.getEvent_description().equals(obj.getEvent_description()) && this.getEnd_time().equals(obj.getEnd_time()) && this.getUser_name().equals(obj.getUser_name())){
            return 1;//means equal
        }
        return 0;//means do not equal
    }

}
