package Events.api_part;

import Events.JDBC.For_Events;
import Events.bean.Events;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



//get used to get events information
//post used to insert a new event information to the database
//put used to update an event information to the database
//delete used to delete an event information from the database

@Path("/events")
public class eve_service {


    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_XML)
    public String getEvent_info(
            @QueryParam("event_id") long event_id,
            @QueryParam("event_title") String event_title,
            @QueryParam("event_description") String event_description,
            @QueryParam("event_user") String event_user,
            @QueryParam("start_t") String start_t,
            @QueryParam("end_t") String end_t
    ) throws Exception {
        Connection conn = null;
        try {
            //creat a event object according to the parameters
            Events A = new Events();
            A.setEvent_id(event_id);
            A.setEvent_title(event_title);
            A.setEvent_description(event_description);
            A.setUser_name(event_user);
            A.setStart_time(start_t);
            A.setEnd_time(end_t);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_start = sdf.parse(start_t);
            Calendar calendar_start = Calendar.getInstance();
            calendar_start.setTime(date_start);

            Date date_end = sdf.parse(end_t);
            Calendar calendar_end = Calendar.getInstance();
            calendar_end.setTime(date_end);

            if (calendar_end.before(calendar_start)) {
                throw new Exception("end time cannot before start time");
            }

            //get connection from mysql database
            conn = For_Events.getConn();

            //search the result from the database
            List<Events> result = For_Events.get(conn, A);

            //xml data header
            String xml_data = "<?xml version=\"1.0\"?>" +
                    "<events>";

            //generate xml data
            for (int i = 0; i < result.size(); i++) {
                xml_data += "<event>";
                xml_data += "<event_ID>" + result.get(i).getEvent_id() + "</event_ID>";
                xml_data += "<title>" + result.get(i).getEvent_title() + "</title>";
                xml_data += "<description>" + result.get(i).getEvent_description() + "</description>";

                xml_data += "<user>" + result.get(i).getUser_name() + "</user>";
                xml_data += "<Start_time>" + result.get(i).getStart_time() + "</Start_time>";
                xml_data += "<End_time>" + result.get(i).getEnd_time() + "</End_time>";

                xml_data += "</event>";
            }

            xml_data += "</events>";

            return xml_data;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //close the connection resource
            For_Events.close(conn);
        }
        return "";
    }

    @POST
    @Path("/post")
    @Produces(MediaType.TEXT_PLAIN)
    public String postStudent_info(
            @QueryParam("event_title") String event_title,
            @QueryParam("event_description") String event_description,
            @QueryParam("event_user") String event_user,
            @QueryParam("start_t") String start_t,
            @QueryParam("end_t") String end_t
    ) throws Exception {
        Connection conn = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_start = sdf.parse(start_t);
            Calendar calendar_start = Calendar.getInstance();
            calendar_start.setTime(date_start);

            Date date_end = sdf.parse(end_t);
            Calendar calendar_end = Calendar.getInstance();
            calendar_end.setTime(date_end);

            if (calendar_end.before(calendar_start)) {
                throw new Exception("end time cannot before start time");
            }

            //creat a event object according to the parameters
            Events A = new Events();
            A.setEvent_title(event_title);
            A.setEvent_description(event_description);
            A.setUser_name(event_user);
            A.setStart_time(start_t);
            A.setEnd_time(end_t);

            //get connection from mysql database
            conn = For_Events.getConn();

            //search the result from the database
            int feedback = For_Events.insert(conn, A);

            //feedback 1 means success, 0 means fail
            return feedback + "";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //close the connection resource
            For_Events.close(conn);
        }
        return "0";

    }

    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudent_info(
            @QueryParam("event_id_s") long event_id_s,
            @QueryParam("event_title_s") String event_title_s,
            @QueryParam("event_description_s") String event_description_s,
            @QueryParam("event_user_s") String event_user_s,
            @QueryParam("start_t_s") String start_t_s,
            @QueryParam("end_t_s") String end_t_s,
            @QueryParam("event_title") String event_title,
            @QueryParam("event_description") String event_description,
            @QueryParam("event_user") String event_user,
            @QueryParam("start_t") String start_t,
            @QueryParam("end_t") String end_t
    ) throws Exception {
        Connection conn = null;
        try {
            //creat two event objects according to the parameters
            Events A_search = new Events();
            A_search.setEvent_id(event_id_s);
            A_search.setEvent_title(event_title_s);
            A_search.setEvent_description(event_description_s);
            A_search.setUser_name(event_user_s);



            A_search.setStart_time(start_t_s);
            A_search.setEnd_time(end_t_s);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_start = sdf.parse(start_t);
            Calendar calendar_start = Calendar.getInstance();
            calendar_start.setTime(date_start);

            Date date_end = sdf.parse(end_t);
            Calendar calendar_end = Calendar.getInstance();
            calendar_end.setTime(date_end);

            if (calendar_end.before(calendar_start)) {
                throw new Exception("end time cannot before start time");
            }

            Events A = new Events();
            A.setEvent_title(event_title);
            A.setEvent_description(event_description);
            A.setUser_name(event_user);
            A.setStart_time(start_t);
            A.setEnd_time(end_t);


            //get connection from mysql database
            conn = For_Events.getConn();

            //search the result from the database
            int result = For_Events.update(conn, A_search, A);

            //1 means success, 0 means fail
            return result + "";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //close the connection resource
            For_Events.close(conn);
        }
        return "0";
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteStudent_info(
            @QueryParam("event_id") long event_id,
            @QueryParam("event_title") String event_title,
            @QueryParam("event_description") String event_description,
            @QueryParam("event_user") String event_user,
            @QueryParam("start_t") String start_t,
            @QueryParam("end_t") String end_t
    ) throws Exception {
        Connection conn = null;
        try {
            //creat a event object according to the parameters
            Events A = new Events();
            A.setEvent_id(event_id);
            A.setEvent_title(event_title);
            A.setEvent_description(event_description);
            A.setUser_name(event_user);
            A.setStart_time(start_t);
            A.setEnd_time(end_t);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_start = sdf.parse(start_t);
            Calendar calendar_start = Calendar.getInstance();
            calendar_start.setTime(date_start);

            Date date_end = sdf.parse(end_t);
            Calendar calendar_end = Calendar.getInstance();
            calendar_end.setTime(date_end);

            if (calendar_end.before(calendar_start)) {
                throw new Exception("end time cannot before start time");
            }

            //get connection from mysql database
            conn = For_Events.getConn();

            //search the result from the database
            int result = For_Events.delete(conn, A);

            //1 means success, 0 means fail
            return result + "";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //close the connection resource
            For_Events.close(conn);
        }
        return "0";
    }


}
