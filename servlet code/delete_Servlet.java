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

@WebServlet(name = "delete_Servlet", value = "/delete_Servlet")
public class delete_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //get the parameter
            String Stu_REST_URI = "http://localhost:9999/Students";
            String stu_GET_PATH = "students/get";
            String stu_DELETE_PATH = "students/delete";

            String Eve_REST_URI = "http://localhost:9998/Events";
            String eve_DELETE_PATH = "events/delete";


            ClientConfig stu_config = new DefaultClientConfig();
            Client stu_client = Client.create(stu_config);
            WebResource stu_service = stu_client.resource(Stu_REST_URI);

            ClientConfig eve_config = new DefaultClientConfig();
            Client eve_client = Client.create(eve_config);
            WebResource eve_service = eve_client.resource(Eve_REST_URI);


            //get the students parameters
            long stu1 = -1;
            if(!request.getParameter("stu_id").equals("")) {
                stu1 = Long.parseLong(request.getParameter("stu_id"));
            }
            String stu2 = (String) request.getParameter("stu_name");
            String stu3 = (String) request.getParameter("stu_number");
            String stu4 = (String) request.getParameter("stu_course");
            if (stu2.equals("")) stu2 = "null";
            if (stu4.equals("")) stu4 = "null";
            if (stu3.equals("")) stu3 = "null";


            //get the event parameters
            String eve = (String) request.getParameter("eve_title");
            String eve2 = (String) request.getParameter("eve_start_time");
            String eve3 = (String) request.getParameter("eve_end_time");
            long eve4 = -1;
            if(!request.getParameter("eve_id").equals("")) {
                eve4 = Long.parseLong(request.getParameter("eve_id"));
            }
            if (eve.equals("")) eve = "null";
            if (eve2.equals("")) eve2 = "2000-01-01 00:00:00";
            if (eve3.equals("")) eve3 = "2000-01-01 00:00:00";


            request.removeAttribute("stu_id");
            request.removeAttribute("stu_name");
            request.removeAttribute("stu_number");
            request.removeAttribute("stu_course");
            request.removeAttribute("eve_start_time");
            request.removeAttribute("eve_title");
            request.removeAttribute("eve_end_time");
            request.removeAttribute("eve_id");

            //if there are no event data pass through, this servlet will be used to delete a student information
            if (eve.equals("null") && eve2.equals("2000-01-01 00:00:00") && eve3.equals("2000-01-01 00:00:00") && eve4 == -1) {
                //delete student data
                //put parameters into delete web request
                MultivaluedMap delete_pa = new MultivaluedMapImpl();
                delete_pa.add("student_id", stu1+"");
                delete_pa.add("student_name", stu2);
                delete_pa.add("student_number", stu3);
                delete_pa.add("student_course", stu4);
                stu_service.path(stu_DELETE_PATH).queryParams(delete_pa).delete();
            } else {
                //put parameters into get web request
                MultivaluedMap get_pa = new MultivaluedMapImpl();
                get_pa.add("student_id", stu1+"");
                get_pa.add("student_name", stu2);
                get_pa.add("student_number", stu3);
                get_pa.add("student_course", stu4);

                Document doc = null;
                try {
                    doc = DocumentHelper.parseText(stu_service.path(stu_GET_PATH).queryParams(get_pa).accept(MediaType.TEXT_XML).get(String.class));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

                Element root = doc.getRootElement();
                Element element = null;
                List<String> stu_name = new ArrayList<String>();
                List<Long> stu_id = new ArrayList<Long>();
                List<String> stu_number = new ArrayList<String>();
                List<String[]> stu_course = new ArrayList<String[]>();
                //handle the XML data return by web service
                for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                    element = (Element) i.next();
                    stu_course.add(element.elementText("course").split("@"));
                    stu_name.add(element.elementText("name"));
                    stu_id.add(Long.parseLong(element.elementText("stu_ID")));
                    stu_number.add(element.elementText("number"));
                }

                for (int eve_count = 0; eve_count < stu_name.size(); eve_count++) {
                    //delete data from event database
                    //put in parameters in to delete web request
                    MultivaluedMap delete_pa = new MultivaluedMapImpl();
                    delete_pa.add("event_id", eve4+"");
                    delete_pa.add("event_title", eve);
                    delete_pa.add("event_description", "null");
                    delete_pa.add("event_user", stu_name.get(eve_count));
                    delete_pa.add("start_t", eve2);
                    delete_pa.add("end_t", eve3);
                    eve_service.path(eve_DELETE_PATH).queryParams(delete_pa).delete();
                }
            }
            request.getSession().setAttribute("error","0");
        }catch (Exception e){
            e.printStackTrace();

            request.getSession().setAttribute("error","1");
        }finally {
            response.sendRedirect("./delete.jsp");

        }




    }
}
