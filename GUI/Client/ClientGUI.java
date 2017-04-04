import java.awt.*;
import javax.swing.*;

public class ClientGUI extends JFrame {
   
   private JFrame jfClientGUI;
//   private final JPanel jpClient;
//
//   private JTextArea jtaClientChatScreen;
//   private JTextField jtfClient;
//   private JScrollPane scroll;
//
//   private JMenuBar jmbClientMenu;
//   private JMenu jmUDP;
//   private JMenu jmTCP;
//   private JMenu jmExit;
//
//   private JPanel jpButtonNorth;
//   private JButton jbUDP;
//   private JButton jbTCP;
//   private JButton jbExit;
   
//   private JPanel jpButtonSouth;
//   private JButton jbSend;
   

   public static void main(String[] arg) {
      new ClientGUI();
   } // main method
   
   public ClientGUI() {
   
      // Create JMenubar for protocol options and to exit the program
         JMenuBar jmbClientMenu = new JMenuBar();
         setJMenuBar(jmbClientMenu);
         
         // Create JMenu - parameter string will show on the JMenuBar
         // TODO: Could use JButtons instead
         JMenu jmUDP = new JMenu("UDP");
         JMenu jmTCP = new JMenu("Test 2");
         JMenu jmQuit = new JMenu("Quit");
         
         // Add JMenu to JMenuBar
         jmbClientMenu.add(jmUDP);
         jmbClientMenu.add(jmTCP);
         jmbClientMenu.add(jmQuit);
   /*
   
      // NORTH
      // Create JButton for protocol options and to exit the program
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
    
   */
    
      // SOUTH;
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