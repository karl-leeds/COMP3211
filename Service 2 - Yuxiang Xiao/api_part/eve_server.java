package Events.api_part;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public class eve_server {
    //initialize the URL
    static String Eve_Server_URI = "http://localhost:9998/Events/";

    public static void main(String[] args) {
        try {
            //creat httpServer object.
            HttpServer server = HttpServerFactory.create(Eve_Server_URI);
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
