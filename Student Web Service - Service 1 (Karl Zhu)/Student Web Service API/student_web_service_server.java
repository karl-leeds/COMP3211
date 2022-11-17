package Students.API;
import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;


public class StudentWebService_Server {
    //initialize the URL
    static String StudentWebService_Server_URL = "http://localhost:9999/StudentWebService/";

    public static void main(String[] args) {
        try {
            //creat httpServer object.
            HttpServer server = HttpServerFactory.create(StudentWebService_Server_URL);
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
