import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ServerGUI extends JFrame {
	
	// main method
	public static void main(String[] arg){
		new ServerGUI();	
	}
	
	public ServerGUI(){
		
		// Create JPanel for quit button
        JPanel jpButtonNorth = new JPanel();
        jpButtonNorth.setPreferredSize(new Dimension(500, 50));
        // Create quit button
        JButton jbQuit = new JButton("Quit");
        jbQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        // Add button to JPanel
        jpButtonNorth.add(jbQuit);
        // Add JPanel to JFrame
        add(jpButtonNorth,BorderLayout.NORTH);


		// JPanel for Start Button
		JPanel jpStartButton = new JPanel();
		
		// Create JButton for starting the server
		jpStartButton.setPreferredSize(new Dimension(500, 50));
		JButton jbStart = new JButton("Start Server");
		jpStartButton.add(jbStart);
		
		// add JPanel to JFrame
		add(jpStartButton, BorderLayout.SOUTH);
		
		// Create JPanel that will contain text for server display
		JPanel jpServer = new JPanel();
		JTextArea jtaServerScreen = new JTextArea(20, 45);
		jtaServerScreen.setEditable(false);
		jtaServerScreen.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(jtaServerScreen);
		jpServer.add(jtaServerScreen);
		
		
		// Add JPanel to JFrame
		add(jpServer);
		
		//Set Frame Properties
		setTitle("Server GUI");
		setLocation(380, 150);
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		
	}
	
}