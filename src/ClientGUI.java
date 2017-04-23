import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;


public class ClientGUI extends JFrame {

    // Class attribute
    // private JFrame jfClientGUI;
    public JTextArea clientChatScreen;
    public String clientName;
    public String protocol;
    public String ip;

    /**
     * Constructor
     */
    public ClientGUI() {

        // Show dialogue on startup - prompt for client input
        JTextField inputName = new JTextField();
        JTextField inputProtocol = new JTextField();
        JTextField inputIp = new JTextField();
        Object[] inputMessages = {
                "Enter Your name:", inputName,
                "TCP/UDP:", inputProtocol,
                "IP Address:", inputIp,
        };
        int option = JOptionPane.showConfirmDialog(null, inputMessages, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            this.clientName = inputName.getText().trim();
            this.protocol = inputProtocol.getText().trim();
            this.ip = inputIp.getText().trim();
        }

        // NORTH
        // Create JButtons for protocol options and to exit the program
        JPanel jpButtonNorth = new JPanel();
        jpButtonNorth.setPreferredSize(new Dimension(500, 50));

        JButton jbQuit = new JButton("Quit");
        jbQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
       
        // Add JButton to JPanel
        jpButtonNorth.add(jbQuit);
        // Add JPanel to JFrame
        add(jpButtonNorth,BorderLayout.NORTH);
    
        // SOUTH
        // JPanel for border south
        JPanel jpButtonSouth = new JPanel();
      
        // Create JTextField for user inputs and add it to south panel
        JTextField message = new JTextField(20);
        jpButtonSouth.add(message);
      
        // Create JButton for sending messages and add it to south panel
        jpButtonSouth.setPreferredSize(new Dimension(500, 50));
        JButton jbSend = new JButton("Send");
        // Add action listener to get client message
        jbSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String text = message.getText();
                message.setText(""); // clear text field
                addMessage(clientName, text);
            }
        });
        jpButtonSouth.add(jbSend);
      
        // Add send text field and button panel to JFrame
        add(jpButtonSouth,BorderLayout.SOUTH);
      
        // Create JPanel
        JPanel jpClient = new JPanel();
        clientChatScreen = new JTextArea(20, 45);
        clientChatScreen.setEditable(false); // Disable the JTextArea editability - making it read-only
        clientChatScreen.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(clientChatScreen);
        jpClient.add(scroll);
        // add(jpClient, BorderLayout.CENTER);
      
        // Add text area panel to JFrame
        add(jpClient);
      
        // Set JFrame property
        setTitle("Client GUI");
        // setLocationRelativeTo(null); //center window - not working
        setLocation(380, 150);
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
      
    } // constructor ClientGUI

    /**
     * Get client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Add new message to clientChatScreen and send to server
     * @param client The client's name who sent message
     * @param msg The client's message to be displayed
     */
    public void addMessage(String client, String msg){
        msg = msg.trim(); // remove whitespace
        msg = client + ": " + msg + "\n";
        clientChatScreen.append(msg);

        try{
            Socket s = new Socket( "localhost", 16789 );
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
    }

    /*
    * Main Method
    * */
    public static void main(String[] arg) {
        ClientGUI gui = new ClientGUI();

        // Create applicable client based on input protocol
        if(gui.protocol.charAt(0) == 'T'){
            TCPClient tcpClient = new TCPClient();
        } else { // UDP
            UDPClient udpClient = new UDPClient();
        }
    }

} // class ClientGUI