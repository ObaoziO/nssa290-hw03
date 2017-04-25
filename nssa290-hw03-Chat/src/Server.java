import java.net.*;
import java.io.*;

/* Server for client / server network communication
*/

public class Server extends Thread{
    InetAddress ipAddress;
    public Server(){
//        this.ipAddress = InetAddress.getLocalHost();
    }

    public void run() {
        String clientMsg;
        BufferedReader br;
        PrintWriter opw;

        try {
            this.ipAddress = InetAddress.getLocalHost();
            System.out.println("getLocalHost: "+ipAddress );
            System.out.println("getByName:    "+InetAddress.getByName("localhost") );
            ServerSocket ss = new ServerSocket(16789);
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
