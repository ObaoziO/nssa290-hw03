import java.net.*;
import java.io.*;

/* Server for client / server network communication
*/

public class Server{
    public static void main(String [] args) {
        String clientMsg;
        ServerSocket ss = null;
        BufferedReader br;
        PrintWriter opw;

        try {
            System.out.println("getLocalHost: "+InetAddress.getLocalHost() );
            System.out.println("getByName:    "+InetAddress.getByName("localhost") );
            ss = new ServerSocket(16789);
            Socket cs = null;
            while(true){ 		// run forever once up
                try{
                    cs = ss.accept(); 				// wait for connection
                    br = new BufferedReader(
                            new InputStreamReader(
                                    cs.getInputStream()));
                    opw = new PrintWriter(
                            new OutputStreamWriter(
                                    cs.getOutputStream()));

                    clientMsg = br.readLine();					// from client
                    System.out.println("Server read: "+ clientMsg);
                    opw.println(clientMsg.toUpperCase());	//to client
                    opw.flush();
                }
                catch( IOException e ) {
                    System.out.println("Inside catch");
                    e.printStackTrace();
                }
            } // end while
        }
        catch( BindException be ) {		// I/O exception catches this
            System.out.println("Server is already running. This server stopping.");
        }
        catch( IOException e ) {
            System.out.println("Outside catch");
            e.printStackTrace();
        }
    }
}
