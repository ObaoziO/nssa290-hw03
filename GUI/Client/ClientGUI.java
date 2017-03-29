import java.awt.*;
import javax.swing.*;

public class ClientGUI extends JFrame {
   
   // private JFrame jfClientGUI;
   private final JPanel jpClient;
   
   private JTextArea jtaClientChatScreen;
   private JTextField jtfClient;
   private JScrollPane scroll;
   
   private JMenuBar jmbClientMenu;
   private JMenu jmUDP;
   private JMenu jmTCP;
   private JMenu jmExit;
   
   private JPanel jpButtonNorth;
   private JButton jbUDP;
   private JButton jbTCP;
   private JButton jbExit;
   
   private JPanel jpButtonSouth;
   private JButton jbSend;
   

   public static void main(String[] arg) {
      new ClientGUI();
   } // main method
   
   public ClientGUI() {
      // Create JFrame 
      // jfClientGUI = new JFrame();
      
   /*    
      // Create JMenubar
          jmbClientMenu = new JMenuBar();
         setJMenuBar(jmbClientMenu);
         
         // Create JMenu - parameter string will show on the JMenuBar
         // TODO: Could use JButtons instead
          jmUDP = new JMenu("UDP");
          jmTCP = new JMenu("Test 2");
          jmQuit = new JMenu("Quit");
         
         // Add JMenu to JMenuBar
         jmbClientMenu.add(jmUDP);
         jmbClientMenu.add(jmTCP);
         jmbClientMenu.add(jmQuit);
   */
   
      // NORTH
      // Create JButton for protocol options and to exit the program
      jpButtonNorth = new JPanel();
      jpButtonNorth.setPreferredSize(new Dimension(500, 50));
       
      jbUDP = new JButton("UDP");
      jbTCP = new JButton("TCP/IP");
      jbExit = new JButton("Exit");
       
       // Add JButton to JPanel
      jpButtonNorth.add(jbUDP);
      jpButtonNorth.add(jbTCP);
      jpButtonNorth.add(jbExit);
      
   	// Add button panel to JFrame
      add(jpButtonNorth,BorderLayout.NORTH);
      
    /*
      // SOUTH
      // Create JButton for sending messages
      jpButtonSouth = new JPanel();
      jpButtonSouth.setPreferredSize(new Dimension(500, 50));
      
      jpButtonSouth.add(jbSend);
       
      jbSend = new JButton("Send");
      jpButtonSouth = new JPanel();
      
      
   	// Add button panel to JFrame
      add(jpButtonSouth,BorderLayout.SOUTH); 
   
   */
      
      // Create JPanel
      jpClient = new JPanel();
      jtaClientChatScreen = new JTextArea(20, 45);
      jtaClientChatScreen.setLineWrap(true);
      scroll = new JScrollPane(jtaClientChatScreen);
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
      
      
   } // constructor GlientGUI

} // class ClientGUI