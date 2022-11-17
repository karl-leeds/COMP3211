package Events.bean;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Events {


    private long event_id;
    private String event_title;
    private String event_description;
    private String user_name;
    private Calendar start_time;
    private Calendar end_time;

    public Events(long event_id, String event_title, String event_description, String user_name, Calendar start_time, Calendar end_time) {
        this.event_id = event_id;
        this.event_title = event_title;
        this.event_description = event_description;
        this.user_name = user_name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Events() {
        this.event_id = -1;
        this.event_title = null;
        this.event_description = null;
        this.user_name = null;
        this.start_time = null;
        this.end_time = null;
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");

        return sdf.format(start_time.getTime());
    }

    public void setStart_time(String start_time) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
        Date date = sdf.parse(start_time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.start_time = calendar;
    }

    public String getEnd_time() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");

        return sdf.format(end_time.getTime());
    }

    public void setEnd_time(String end_time) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
        Date date = (Date) sdf.parse(end_time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.end_time = calendar;
    }

    public int equal(Events obj){
        //if title, description, start time, end time and user information are equals, which means these two object are equal
        if(this.getEvent_title().equals(obj.getEvent_title()) && this.getStart_time().equals(obj.getStart_time()) && this.getEvent_description().equals(obj.getEvent_description()) && this.getEnd_time().equals(obj.getEnd_time()) && this.getUser_name().equals(obj.getUser_name())){
            return 1;//means equal
        }
        return 0;//means do not equal
    }

}
