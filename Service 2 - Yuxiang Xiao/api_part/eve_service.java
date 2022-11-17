package Events.api_part;

import Events.JDBC.For_Events;
import Events.bean.Events;
import Students.JDBC.For_students;
import Students.bean.Students;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
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

        //creat a event object according to the parameters
        Events A = new Events();
        A.setEvent_id(event_id);
        A.setEvent_title(event_title);
        A.setEvent_description(event_description);
        A.setUser_name(event_user);
        A.setStart_time(start_t);
        A.setEnd_time(end_t);


        //get connection from mysql database
        Connection conn = For_Events.getConn();

        //search the result from the database
        List<Events> result = For_Events.get(conn,A);

        //xml data header
        String xml_data = "<?xml version=\"1.0\"?>" +
                "<events>";

        //generate xml data
        for(int i = 0; i < result.size(); i++){
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
        System.out.println("afddfa\n");

        //creat a event object according to the parameters
        Events A = new Events();
        A.setEvent_title(event_title);
        A.setEvent_description(event_description);
        A.setUser_name(event_user);
        A.setStart_time(start_t);
        A.setEnd_time(end_t);

        //get connection from mysql database
        Connection conn = For_Events.getConn();

        //search the result from the database
        int feedback = For_Events.insert(conn,A);

        //feedback 1 means success, 0 means fail
        return feedback + "";
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
        //creat two event objects according to the parameters

        Events A_search = new Events();
        A_search.setEvent_id(event_id_s);
        A_search.setEvent_title(event_title_s);
        A_search.setEvent_description(event_description_s);
        A_search.setUser_name(event_user_s);
        A_search.setStart_time(start_t_s);
        A_search.setEnd_time(end_t_s);


        Events A = new Events();
        A.setEvent_title(event_title);
        A.setEvent_description(event_description);
        A.setUser_name(event_user);
        A.setStart_time(start_t);
        A.setEnd_time(end_t);


        //get connection from mysql database
        Connection conn = For_Events.getConn();

        //search the result from the database
        int result = For_Events.update(conn,A_search,A);

        //1 means success, 0 means fail
        return result + "";
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
        //creat a event object according to the parameters
        Events A = new Events();
        A.setEvent_id(event_id);
        A.setEvent_title(event_title);
        A.setEvent_description(event_description);
        A.setUser_name(event_user);
        A.setStart_time(start_t);
        A.setEnd_time(end_t);

        //get connection from mysql database
        Connection conn = For_Events.getConn();

        //search the result from the database
        int result = For_Events.delete(conn,A);

        //1 means success, 0 means fail
        return result+"";
    }


}
