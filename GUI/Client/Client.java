/**
 * Created by henrykirk on 4/12/17.
 */
public class Client {

    // class variables
    String clientName = "";
    ClientGUI clientGUI ;

    /*
    * Constructor
    * */
    public Client() {
        ClientGUI clientGUI = new ClientGUI();
        this.clientName = clientGUI.getClientName();
    }

    /*
    * Main Method
    * */
    public static void main(String[] arg) {
        Client client = new Client();
    }
}
