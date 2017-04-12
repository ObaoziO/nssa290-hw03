import java.util.*;
import java.net.*;
import java.io.*;

public class Client {

    // class variables
//    String clientName = "";
    ClientGUI clientGUI ;
    static final String IP = "10.100.100.29";
    static final int PORT = 15789;
    BufferedReader in;
    PrintWriter out;
    Socket cs = null;

    /*
    * Constructor
    * */
    public Client() {
        ClientGUI clientGUI = new ClientGUI();
//        this.clientName = clientGUI.getClientName();
        try {

            // open a socket
            cs = new Socket(IP, PORT);
            // to get streams
            InputStreamReader instream = new InputStreamReader(cs.getInputStream());
            OutputStream outstream = cs.getOutputStream();

            in = new BufferedReader(instream);
            out = new PrintWriter(outstream);


            while (true) {
                if (in.ready()) {
                    clientGUI.addMessage("client1", in.readLine());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                // closing socket
                cs.close();
            } catch (Exception e) {
                System.out.println("Error ... " + e.getMessage());
            }
        }

    } // constructor

    /*
    * Main Method
    * */
    public static void main(String[] arg) {
        Client client = new Client();
    }
}
