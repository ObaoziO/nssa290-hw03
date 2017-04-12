import java.awt.*;
import javax.swing.*;


public class ServerGUI extends JFrame {
	
	// main method
	public static void main(String[] arg){
		new ServerGUI();	
	}
	
	public ServerGUI(){
		
		// file options and option to properly close the server
		JMenuBar jmbClientMenu = new JMenuBar();
		setJMenuBar(jmbClientMenu);
		
		// Create JMenu 
		JMenu jmFile = new JMenu("File");
		JMenu jmQuit = new JMenu("Quit");
		
		// Add to JMenu
		jmbClientMenu.add(jmFile);
		jmbClientMenu.add(jmQuit);
		
		// JPanel for Start Button
		JPanel jpStartButton = new JPanel();
		
		
		// Creae JButton for starting the server
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