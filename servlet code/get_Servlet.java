import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "get_Servlet", value = "/get_Servlet")
public class get_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String Stu_REST_URI = "http://localhost:9999/Students";
            String stu_GET_PATH = "students/get";

            String Eve_REST_URI = "http://localhost:9998/Events";
            String eve_GET_PATH = "events/get";


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
            if (stu3.equals("")) stu3 = "null";
            if (stu4.equals("")) stu4 = "null";

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


            //put parameters into get web request
            MultivaluedMap get_pa = new MultivaluedMapImpl();
            get_pa.add("student_id", stu1+"");
            get_pa.add("student_name", stu2);
            get_pa.add("student_number", stu3);
            get_pa.add("student_course", stu4);


            String pdf_body = "{\"name\":\"New Template\",\"tags\":[],\"isDraft\":false,\"layout\":{\"format\":\"A4\",\"unit\":\"cm\",\"orientation\":\"portrait\",\"rotation\":0,\"margins\":{\"top\":0.5,\"left\":0.5,\"right\":0.5,\"bottom\":0.5},\"emptyLabels\":0,\"width\":21,\"height\":29.7,\"repeatLayout\":null},\"pages\":[{\"width\":21,\"height\":29.7,\"conditionalFormats\":[],\"backgroundImage\":null,\"layout\":[],\n" +
                    "\n" +
                    "\"components\":[\n" +
                    "    {\"width\":19.94,\"height\":1.68,\"id\":\"\",\"top\":0.08,\"left\":0,\"zindex\":1001,\"phpClassName\":\"Reports_Component_Table\",\"className\":null,\"name\":null,\"value\":null,\"dataIndex\":\"\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":true,\"left\":true},\"borderWidth\":1,\"borderColor\":\"#000000\",\"borderStyle\":\"solid\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"padding\":{\"top\":0,\"right\":0,\"bottom\":0,\"left\":0},\"conditionalFormats\":[],\"fontFamily\":null,\"fontAlign\":null,\"fontSize\":null,\"fontType\":[],\"fontColor\":null,\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"tableComponent\",\n" +
                    "    \n" +
                    "    \"rows\":[\n" +
                    "        {\"isHeader\":true,\"isStatic\":true,\"columns\":[\n" +
                    "        {\"width\":0.75,\"height\":0,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\"id\",\"defaultValue\":false,\"dataIndex\":\"randomDataIndexForFlexHeight\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":false,\"left\":true},\"borderWidth\":1,\"borderColor\":\"#000000\",\"borderStyle\":\"solid\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"bottom\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                    "        {\"width\":2.49,\"height\":0,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\"student_name\",\"defaultValue\":false,\"dataIndex\":\"randomDataIndexForFlexHeight\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":false,\"left\":true},\"borderWidth\":1,\"borderColor\":\"#000000\",\"borderStyle\":\"solid\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"bottom\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                    "        {\"width\":2.65,\"height\":0,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\"student number\",\"defaultValue\":false,\"dataIndex\":\"randomDataIndexForFlexHeight\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":false,\"left\":true},\"borderWidth\":1,\"borderColor\":\"#000000\",\"borderStyle\":\"solid\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"bottom\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                    "        {\"width\":2.83,\"height\":0,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\"title\",\"defaultValue\":false,\"dataIndex\":\"randomDataIndexForFlexHeight\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":false,\"left\":true},\"borderWidth\":1,\"borderColor\":\"#000000\",\"borderStyle\":\"solid\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"bottom\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                    "        {\"width\":7.2,\"height\":0,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\"description\",\"defaultValue\":false,\"dataIndex\":\"randomDataIndexForFlexHeight\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":false,\"left\":true},\"borderWidth\":1,\"borderColor\":\"#000000\",\"borderStyle\":\"solid\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"bottom\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                    "        {\"width\":2.12,\"height\":0,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\"start time\",\"defaultValue\":false,\"dataIndex\":\"randomDataIndexForFlexHeight\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":false,\"left\":true},\"borderWidth\":1,\"borderColor\":\"#000000\",\"borderStyle\":\"solid\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"bottom\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                    "        {\"width\":1.88,\"height\":0,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\"end time\",\"defaultValue\":false,\"dataIndex\":\"randomDataIndexForFlexHeight\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":false,\"left\":true},\"borderWidth\":1,\"borderColor\":\"#000000\",\"borderStyle\":\"solid\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"bottom\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"}\n" +
                    "    ]\n" +
                    "    }";

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
                MultivaluedMap eve_get_pa = new MultivaluedMapImpl();
                eve_get_pa.add("event_id", eve4 + "");
                eve_get_pa.add("event_title", eve);
                eve_get_pa.add("event_description", "null");
                eve_get_pa.add("event_user", stu_name.get(eve_count));
                eve_get_pa.add("start_t", eve2 + "");
                eve_get_pa.add("end_t", eve3);
                String xml_file = eve_service.path(eve_GET_PATH).queryParams(eve_get_pa).accept(MediaType.TEXT_XML).get(String.class);

                try {
                    doc = DocumentHelper.parseText(xml_file);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                root = doc.getRootElement();
                List<String> eve_title = new ArrayList<String>();
                List<Long> eve_id = new ArrayList<Long>();
                List<String> eve_user = new ArrayList<String>();
                List<String> eve_start = new ArrayList<String>();
                List<String> eve_end = new ArrayList<String>();
                List<String> eve_description = new ArrayList<String>();

                //handle the XML data return by web service
                for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                    element = (Element) i.next();

                    eve_id.add(Long.parseLong(element.elementText("event_ID")));
                    eve_title.add(element.elementText("title"));
                    eve_description.add(element.elementText("description"));
                    eve_user.add(element.elementText("user"));
                    eve_start.add(element.elementText("Start_time"));
                    eve_end.add(element.elementText("End_time"));

                    pdf_body += ",{\"isHeader\":false,\"isStatic\":false,\"columns\":[\n" +
                            "        {\"width\":0.75,\"height\":0.52,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\""+element.elementText("event_ID")+"\",\"defaultValue\":false,\"dataIndex\":\"\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":true,\"left\":true},\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"none\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"top\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                            "        \n" +
                            "        {\"width\":2.49,\"height\":0.52,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\""+element.elementText("user")+"\",\"defaultValue\":false,\"dataIndex\":\"\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":true,\"left\":true},\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"none\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"top\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                            "        \n" +
                            "        {\"width\":2.65,\"height\":0.52,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\""+stu_number.get(eve_count)+"\",\"defaultValue\":false,\"dataIndex\":\"\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":true,\"left\":true},\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"none\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"top\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                            "        \n" +
                            "        {\"width\":2.83,\"height\":0.52,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\""+element.elementText("title")+"\",\"defaultValue\":false,\"dataIndex\":\"\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":true,\"left\":true},\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"none\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"top\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                            "        \n" +
                            "        {\"width\":7.2,\"height\":0.52,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\""+element.elementText("description")+"\",\"defaultValue\":false,\"dataIndex\":\"\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":true,\"left\":true},\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"none\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"top\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                            "        \n" +
                            "        {\"width\":2.12,\"height\":0.52,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\""+element.elementText("Start_time")+"\",\"defaultValue\":false,\"dataIndex\":\"\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":true,\"left\":true},\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"none\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"top\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"},\n" +
                            "        \n" +
                            "        {\"width\":1.88,\"height\":0.52,\"id\":\"\",\"top\":0,\"left\":0,\"zindex\":1000,\"phpClassName\":\"Reports_Component_CustomText\",\"className\":null,\"name\":null,\"preview\":false,\"value\":\""+element.elementText("End_time")+"\",\"defaultValue\":false,\"dataIndex\":\"\",\"borderStatus\":{\"top\":true,\"right\":true,\"bottom\":true,\"left\":true},\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"none\",\"backgroundColor\":\"\",\"useFlexHeight\":true,\"isEditable\":false,\"autoShrink\":false,\"isPlainHTML\":false,\"userRights\":{\"canEdit\":true,\"canMove\":true,\"canRemove\":true,\"canResize\":true},\"padding\":{\"top\":0.08,\"right\":0.08,\"bottom\":0.08,\"left\":0.08},\"conditionalFormats\":[],\"fontFamily\":\"opensans\",\"fontAlign\":\"left\",\"fontSize\":12,\"fontType\":[],\"fontColor\":\"#000000\",\"valign\":\"top\",\"lineHeight\":1.15,\"textDirection\":\"ltr\",\"textColumns\":1,\"dynamicFontSize\":false,\"cls\":\"labelComponent\"}]}";
                }
            }

            pdf_body += "],\"isDynamic\":false,\"hideHeaderIfEmpty\":true,\"pivotOn\":[],\"pivotColumns\":[],\"pivotValues\":[],\"sortBy\":[],\"sortDir\":\"ASC\",\"filterBy\":[],\"groupBy\":[],\"fontValign\":\"top\"}],\"margins\":{\"right\":0.5,\"bottom\":0.5},\"border\":false}],\"dataSettings\":{\"sortBy\":[],\"filterBy\":[]},\"editor\":{\"heightMultiplier\":1},\"fontSubsetting\":false,\"barcodeAsImage\":false}";



            String h_url = "";
            //get third web service serve
            String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIyOGI2ZWU0OWM1ZjRhZTFjM2ExMTUyY2YyNGMzM2NlZjY1M2M3OGEyZGY2MDE1N2JjZmFhNTQ5YzVkYzkxNmU2Iiwic3ViIjoic2MyMHl4QGxlZWRzLmFjLnVrIiwiZXhwIjoxNjY5MDQ2ODAwfQ.QHBqxkjIFMteslgDdagu7ToYTc3vESfhGbZdvADzUQw";
            HttpResponse<String> post_response = null;
            try {


                post_response = Unirest.post("https://us1.pdfgeneratorapi.com/api/v4/templates")
                        .header("content-type", "application/json")
                        .header("authorization", "Bearer " + jwt)

                        .body(pdf_body)
                        .asString();

                JSONObject back = new JSONObject(post_response.getBody());
                JSONObject ll = (JSONObject) back.get("response");

                String bo = "{\"template\":{\"id\":\"" + ll.get("id") + "\"} ,\"output\":\"url\"}";
                HttpResponse<String> response1 = Unirest.post("https://us1.pdfgeneratorapi.com/api/v4/documents/generate")
                        .header("content-type", "application/json")

                        .header("authorization", "Bearer " + jwt)

                        .body(bo)
                        .asString();
                JSONObject back3 = new JSONObject(response1.getBody());
                h_url = (String) back3.get("response");
                h_url.replace("\\", "");

                HttpResponse<String> response2 = Unirest.delete("https://us1.pdfgeneratorapi.com/api/v4/templates/" + ll.get("id") + "")
                        .header("content-type", "application/json")

                        .header("authorization", "Bearer " + jwt)
                        .asString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("http_url", h_url);
            request.getSession().setAttribute("error", "0");
        }catch (Exception e){
            e.printStackTrace();

            request.getSession().setAttribute("error", "1");
        }finally {
            response.sendRedirect("./retrieve.jsp");

        }
    }
}
