import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "put_Servlet", value = "/put_Servlet")
public class put_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //get the parameter
            String Stu_REST_URI = "http://localhost:9999/Students";
            String stu_PUT_PATH = "students/update";

            String Eve_REST_URI = "http://localhost:9998/Events";
            String eve_PUT_PATH = "events/update";


            ClientConfig stu_config = new DefaultClientConfig();
            Client stu_client = Client.create(stu_config);
            WebResource stu_service = stu_client.resource(Stu_REST_URI);

            ClientConfig eve_config = new DefaultClientConfig();
            Client eve_client = Client.create(eve_config);
            WebResource eve_service = eve_client.resource(Eve_REST_URI);


            //get the students parameters
            long s_stu1 = -1;
            if(!request.getParameter("Locat_stu_id").equals("")) {
                s_stu1 = Long.parseLong(request.getParameter("Locat_stu_id"));
            }

            String s_stu2 = (String) request.getParameter("Locat_stu_name");
            String s_stu3 = (String) request.getParameter("Locat_stu_number");
            String s_stu4 = (String) request.getParameter("Locat_stu_course");


            String stu2 = (String) request.getParameter("stu_name");
            String stu3 = (String) request.getParameter("stu_number");
            String stu4 = (String) request.getParameter("stu_course");
            if (s_stu2.equals("")) s_stu2 = "null";
            if (s_stu3.equals("")) s_stu3 = "null";
            if (s_stu4.equals("")) s_stu4 = "null";

            if (stu2.equals("")) stu2 = "null";
            if (stu3.equals("")) stu3 = "null";
            if (stu4.equals("")) stu4 = "null";


            //get the event parameters
            String s_eve = (String) request.getParameter("Locat_eve_title");
            String s_eve5 = (String) request.getParameter("Locat_user_name");
            String s_eve2 = (String) request.getParameter("Locat_eve_start_time");
            String s_eve3 = (String) request.getParameter("Locat_eve_end_time");
            long s_eve4 = -1;
            if(!request.getParameter("Locat_eve_id").equals("")) {
                s_eve4 = Long.parseLong(request.getParameter("Locat_eve_id"));
            }

            String eve = (String) request.getParameter("eve_title");
            String eve5 = (String) request.getParameter("user_name");
            String eve1 = (String) request.getParameter("eve_description");
            String eve2 = (String) request.getParameter("eve_start_time");
            String eve3 = (String) request.getParameter("eve_end_time");
            if (s_eve.equals("")) s_eve = "null";
            if (s_eve5.equals("")) s_eve5 = "null";
            if (s_eve2.equals("")) s_eve2 = "2000-01-01 00:00:00";
            if (s_eve3.equals("")) s_eve3 = "2000-01-01 00:00:00";

            if (eve.equals("")) eve = "null";
            if (eve5.equals("")) eve5 = "null";
            if (eve1.equals("")) eve1 = "null";
            if (eve2.equals("")) eve2 = "2000-01-01 00:00:00";
            if (eve3.equals("")) eve3 = "2000-01-01 00:00:00";


            if (!stu2.equals("null") || !stu3.equals("null") || !stu4.equals("null")) {
                //update data
                //put parameters into put web request
                MultivaluedMap update_pa = new MultivaluedMapImpl();
                update_pa.add("student_id_s", s_stu1+"");
                update_pa.add("student_name_s", s_stu2);
                update_pa.add("student_number_s", s_stu3);
                update_pa.add("student_course_s", s_stu4);

                update_pa.add("student_name", stu2);
                update_pa.add("student_number", stu3);
                update_pa.add("student_course", stu4);
                stu_service.path(stu_PUT_PATH).queryParams(update_pa).put();
            }

            if (!eve.equals("null") || !eve2.equals("2000-01-01 00:00:00") || !eve3.equals("2000-01-01 00:00:00") || !eve1.equals("null") || !eve5.equals("null")) {

                MultivaluedMap update_pa = new MultivaluedMapImpl();
                update_pa.add("event_id_s", s_eve4+"");
                update_pa.add("event_title_s", s_eve);
                update_pa.add("event_description_s", "null");
                update_pa.add("event_user_s", s_eve5);
                update_pa.add("start_t_s", s_eve2);
                update_pa.add("end_t_s", s_eve3);

                update_pa.add("event_title", eve);
                update_pa.add("event_description", eve1);
                update_pa.add("event_user", eve5);
                update_pa.add("start_t", eve2);
                update_pa.add("end_t", eve3);
                eve_service.path(eve_PUT_PATH).queryParams(update_pa).put();
            }
            request.getSession().setAttribute("error","0");

        }catch (Exception e){
            e.printStackTrace();

            request.getSession().setAttribute("error","1");
        }finally {
            response.sendRedirect("./update.jsp");

        }

    }
}
