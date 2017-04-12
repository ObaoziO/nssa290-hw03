import java.awt.*;
import javax.swing.*;

public class ClientGUI extends JFrame {
   
   private JFrame jfClientGUI; 

   public static void main(String[] arg) {
      new ClientGUI();
   } // main method
   
   public ClientGUI() { 
   
      // NORTH
      // Create JButtons for protocol options and to exit the program
      JPanel jpButtonNorth = new JPanel();
      jpButtonNorth.setPreferredSize(new Dimension(500, 50));
       
      JButton jbUDP = new JButton("UDP");
      JButton jbTCP = new JButton("TCP/IP");
      JButton jbExit = new JButton("Exit");
       
       // Add JButton to JPanel
      jpButtonNorth.add(jbUDP);
      jpButtonNorth.add(jbTCP);
      jpButtonNorth.add(jbExit);
      
      // Add button panel to JFrame
      add(jpButtonNorth,BorderLayout.NORTH);
    
   
    
      // SOUTH
      // JPanel for border south
      JPanel jpButtonSouth = new JPanel();
      
      // Create JTextField for user inputs and add it to south panel
      JTextField textField = new JTextField(20);
      jpButtonSouth.add(textField);
      
      // Create JButton for sending messages and add it to south panel
      jpButtonSouth.setPreferredSize(new Dimension(500, 50));
      JButton jbSend = new JButton("Send");
      jpButtonSouth.add(jbSend);
      
      // Add send text field and button panel to JFrame
      add(jpButtonSouth,BorderLayout.SOUTH);
            
      
      // Create JPanel
      JPanel jpClient = new JPanel();
      JTextArea jtaClientChatScreen = new JTextArea(20, 45);
      jtaClientChatScreen.setEditable(false); // Disable the JTextArea editability - making it read-only
      jtaClientChatScreen.setLineWrap(true);
      JScrollPane scroll = new JScrollPane(jtaClientChatScreen);
      jpClient.add(scroll);
      // add(jpClient, BorderLayout.CENTER);
      
      // Add text area panel to JFrame
      add(jpClient);
      
      // Set JFrame property
      setTitle("Client GUI");
      //  setLocationRelativeTo(null); //center window - not working
      setLocation(380, 150);
      setSize(600, 500);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      
      
   } // constructor ClientGUI

} // class ClientGUI