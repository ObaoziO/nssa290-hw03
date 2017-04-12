import java.io.*;
import java.net.*;

public class Server {

    public static void main(String [] args){

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

            System.out.println("client sent "+ clientMsg );

        }
        catch( Exception e ){
            e.printStackTrace();
        }



    } // end main
} // ebd class