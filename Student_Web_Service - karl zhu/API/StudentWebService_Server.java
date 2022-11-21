package Student_Web_Service.API;
import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;


public class StudentWebService_Server {
    //Initialize the URL of the server
    static String StudentServerURL = "http://localhost:9999/Students/";

    public static void main(String[] args) {
        try {
            //Creating the HTTPServer object.
            HttpServer server = HttpServerFactory.create(StudentServerURL);
            server.start();
            System.out.println("Press Enter to stop the server. ");
            System.in.read();
            server.stop(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
