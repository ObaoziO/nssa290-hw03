import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ClientGUI extends JFrame {

    // Class attribute
    // private JFrame jfClientGUI;
    public JTextArea clientChatScreen;
    public String clientName;
    public String protocol;
    public String ip;
    private Client client;

    /**
     * Constructor
     */
    public ClientGUI() {
        // Create new client
        this.client = new Client();

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
            // TODO - change TCP/UDP to radio button
            this.clientName = inputName.getText().trim();
            this.protocol = inputProtocol.getText().trim();
            this.ip = inputIp.getText().trim();
        }

        // NORTH
        // Create JButton to quit the program
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
     * Add new message to clientChatScreen and call client's addMessage()
     * @param clientName The client's name who sent message
     * @param msg The client's message to be displayed
     */
    private void addMessage(String clientName, String msg){
        msg = msg.trim(); // remove whitespace
        msg = clientName + ": " + msg + "\n";
        clientChatScreen.append(msg);
        client.addMessage(msg);
    }


    /**
     * Main Method
     */
    public static void main(String[] arg) {
        ClientGUI gui = new ClientGUI();
        gui.client.addMessage("test");
    }



} // class ClientGUI