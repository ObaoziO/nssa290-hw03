import java.net.*;
import java.io.*;

<<<<<<< HEAD
public class Server {

   public static void main(String [] args){
   
      ClientGUI clientGUI = new ClientGUI();
   
      try{
         System.out.println("Server IP is "+ InetAddress.getLocalHost() );
      
         ServerSocket ss = new ServerSocket( 16789 );
      
         Socket cs = null;
      
         System.out.println("Waiting for a client");
         cs = ss.accept();       // waits here until a client connects, then we have cs object
         System.out.println("Have a client");
      
         BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        cs.getInputStream()));
      
         String clientMsg = br.readLine();
      
         // System.out.println( "Client send: "+ clientMsg );
         System.out.println("Client \"" + clientGUI.clientName + "\" send: "+ clientMsg );
      
      }
      catch( Exception e ){
         e.printStackTrace();
      }
   
   
   
   } // end main
} // ebd class
=======
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
>>>>>>> 610c9a013658ab0b006f75b122a69dcf879c23ff
