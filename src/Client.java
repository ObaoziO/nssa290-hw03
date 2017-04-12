import java.util.*;
import java.net.*;
import java.io.*;

public class Client {

    // class variables
//    String clientName = "";
    public ClientGUI clientGUI ;

    /*
    * Constructor
    * */
    public Client() {
        ClientGUI clientGUI = new ClientGUI();
//        this.clientName = clientGUI.getClientName();


    } // constructor

    /*
    * Main Method
    * */
    public static void main(String[] arg) {
        Client client = new Client();
    }
}
