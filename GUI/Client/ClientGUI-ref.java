import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
Client class to make a connection with a server
*/
public class ClientGUI extends JFrame { //  implements ActionListener
 

	// class attributes
 //   static final String IP = "10.100.100.29";
//    static final int PORT = 15789;
   JTextArea chatTextArea;
   JTextField userTextField;
   JButton sendButton;
   // BufferedReader in;
//    PrintWriter out;
//    Socket cs =null;
//    String name;

// The entry main() method
   public static void main(String[] args) {
      // Invoke the constructor (to setup the GUI) by allocating an instance
      new ClientGUI();
   }
	
   public ClientGUI()
   {
      // this.name();
   	// building the GUI
      JPanel chatPanel = new JPanel();
      chatTextArea = new JTextArea(19,40);
      chatTextArea.setLineWrap(true); // to wrap the text
      JScrollPane scroll = new JScrollPane(chatTextArea); // to make it scrollable
      chatPanel.add(scroll);
      add(chatPanel);
   	
   	// building textfield and button
      // JPanel container = new JPanel();	
//       userTextField = new JTextField(20);
//       sendButton = new JButton("Send");
//      //  sendButton.addActionListener(this);
//       container.add(userTextField);
//       container.add(sendButton);
//       add(container, BorderLayout.SOUTH);
//    	
   	
   	// window
      setTitle("NSSA 290 - HW 3");
//       setLocationRelativeTo(null);
      setLocation(500,200);
      setSize(500,400);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   
       //create & and menus
      JMenuBar jmb = new JMenuBar();
      setJMenuBar(jmb);
      // add file menu
      JMenu jFile = new JMenu("File");
      //add help menu
      JMenu jHelp = new JMenu("Help");
      // add it
      jmb.add(jFile);
      jmb.add(jHelp);
   	// add Menu Item about, reset, exet
      JMenuItem jmiAbout = new JMenuItem("About");
      JMenuItem jmiExit = new JMenuItem("Exit");
      // add it to the menu
      jFile.add(jmiExit);
      jHelp.add(jmiAbout);
      
   //register Listeners
      // Listener helpListener=new Listener(this);
//       jmiAbout.addActionListener(helpListener);      
//       jmiExit.addActionListener(helpListener);      
      
    //addWindowListener
      addWindowListener(
            new WindowAdapter() {
               public void windowClosing(WindowEvent ev) {
                  JOptionPane.showMessageDialog(null,"Thank you for Using Our Chat Program") ;
                  System.exit(0);            }
            });
   
     //  try{
//       
//       		// open a socket
//          cs = new Socket(IP, PORT);
//       		// to get streams
//          InputStreamReader instream = new InputStreamReader(cs.getInputStream());
//          OutputStream outstream = cs.getOutputStream();
//             
//          in = new BufferedReader(instream);
//          out = new PrintWriter(outstream);
//       		
//       		
//          while(true){					
//             if(in.ready())
//             {
//                chatTextArea.append(in.readLine() + "\n");
//             }
//          }}
//       catch(Exception e)
//       {
//          System.out.println(e.getMessage());
//       }
//       finally
//       {
//          try
//          {
//          		// closing socket
//             cs.close();
//          }
//          catch(Exception e)
//          {
//             System.out.println("Error ... "+e.getMessage());}
//       }
//    
   
   }
	
   	
}