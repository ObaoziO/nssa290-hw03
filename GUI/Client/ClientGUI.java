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
    JTextArea clientChatScreen ;
    String clientName ;

    /**
     * Constructor
     */
    public ClientGUI() {

        this.clientName = JOptionPane.showInputDialog(null, "What is your name?").trim();

        // NORTH
        // Create JButtons for protocol options and to exit the program
        JPanel jpButtonNorth = new JPanel();
        jpButtonNorth.setPreferredSize(new Dimension(500, 50));

        JButton jbUDP = new JButton("UDP");
        JButton jbTCP = new JButton("TCP/IP");
        JButton jbExit = new JButton("Exit");
        jbExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
       
        // Add JButtons to JPanel
        jpButtonNorth.add(jbUDP);
        jpButtonNorth.add(jbTCP);
        jpButtonNorth.add(jbExit);
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
                try{
                    Socket s = new Socket( "localhost", 16789 );
                    InputStream in = s.getInputStream();
                    BufferedReader bin = new BufferedReader(new InputStreamReader( in ));

                    OutputStream out = s.getOutputStream();
                    PrintWriter pout = new PrintWriter(out);

                    pout.println( text );
                    pout.flush();

                }
                catch( UnknownHostException un ){
                    un.printStackTrace();
                }
                catch( IOException ioe){
                    ioe.printStackTrace();
                }
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
     * Add new message to clientChatScreen
     * @param client The client's name who sent message
     * @param msg The client's message to be displayed
     */
    public void addMessage(String client, String msg){
        msg = msg.trim(); // remove whitespace
        clientChatScreen.append(client + ": " + msg + "\n");
    }

} // class ClientGUI