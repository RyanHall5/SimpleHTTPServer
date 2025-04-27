import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
public class httpServer {

  public static void main(String[] args) throws Exception {

    //creating server 
    final ServerSocket server = new ServerSocket(8080);
    System.out.println("Listening for connection on port 8080 ....");

    //counting number of connections made during server's life
    int count = 0;

    while (true){
      //waiting for connection
      try(Socket client  = server.accept()){

        //adding one server connection to count
        count++;

        //converting connection http header to displayable text
        InputStreamReader isr = new InputStreamReader(client.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        String line = reader.readLine();

        //displaying connection text
        while(line != null && !line.isEmpty()){
          System.out.println(line);
          line = reader.readLine();
        }

        //sending response
        Date today = new Date();
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today + " Super secret message don't tell anyone ";
        client.getOutputStream().write(httpResponse.getBytes("UTF-8"));
        
        //final message to console to confirm completion
        System.out.println("Connection #" + count +" Compelte.");
      }
    }
  }
}