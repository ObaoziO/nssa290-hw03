import java.util.*;
import java.net.*;
import java.io.*;

public class Client {

    // TODO - get this value from clientGUI input
    private char protocol = 'T'; // 'T' or 'U'

    /**
     * Add new message to clientChatScreen and send to server
     * @param msg The client's message to be displayed (includes client's name)
     */
    public void addMessage(String msg){
        // TODO - dynamically create port?
        int port = 16789;

        if(protocol == 'T'){ // TCP/IP
            try{
                Socket s = new Socket( "localhost", port);
                InputStream in = s.getInputStream();
                BufferedReader bin = new BufferedReader(new InputStreamReader( in ));

                OutputStream out = s.getOutputStream();
                PrintWriter pout = new PrintWriter(out);

                pout.println( msg );
                pout.flush();

            }
            catch( UnknownHostException un ){
                un.printStackTrace();
            }
            catch( IOException ioe){
                ioe.printStackTrace();
            }
        } else { // UDP
            BufferedReader br = null;
            String exit = "exit";

            try{
                //TODO - make UDP work here
                while(true){
//                    System.out.print("Please enter your message: ");
//                    br = new BufferedReader(new InputStreamReader(System.in));
//                    message = br.readLine();
                    DatagramSocket ds = new DatagramSocket();
//                    if(message.toLowerCase().equals(exit)){
//                        ds.close();
//                        return;
//                    }
                    byte[] data = msg.getBytes();
                    InetAddress addr = InetAddress.getByName("192.168.1.127");
                    DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
                    ds.send(pack);
                }
            }
            catch ( Exception e ) {
                System.out.println("An Unspecified Error Has Occured");
            }
        }
    }
}
