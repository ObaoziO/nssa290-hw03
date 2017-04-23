import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.*;
//TODO - have jlTp show server IPAddress to connect to

public class ServerGUI extends JFrame {
//    private String ipAddress;
    JLabel jlIp ;

	// main method
	public static void main(String[] arg){
        Server server = new Server();
        server.start();
	    ServerGUI gui = new ServerGUI();
        gui.setIpAddressLabel(server.ipAddress.toString());
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
        // Create JLabel to show IP address needed to connect
        jlIp = new JLabel();
        // Add button to JPanel
        jpButtonNorth.add(jbQuit);
        // Add label to JPanel
        jpButtonNorth.add(jlIp);
        // Add JPanel to JFrame
        add(jpButtonNorth,BorderLayout.NORTH);

		
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

	private void setIpAddressLabel(String ip){
	    jlIp.setText(ip);
    }
	
}