import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * ServerGUI
 */
public class ServerGUI extends JFrame implements WindowListener, ActionListener {

    // JTextArea for eventLog
    private JTextArea eventLog;
	// stop/start button
	private JButton jbStartStop;
	// port number TextField
	private JTextField tfPort;
	private final int defaultPort = 1678;
	private TCPServer server = null;
	private UDPServer udpServer = null;
	
	
	// Constructor
	ServerGUI() {
		// NORTH
        // Holds port input and start/stop button
		JPanel jpNorth = new JPanel();
		jpNorth.add(new JLabel("Enter Port Number: "));
		tfPort = new JTextField(Integer.toString(defaultPort));
		jpNorth.add(tfPort);
		jbStartStop = new JButton("Start Server");
		// add event listener to stop/start Server on click
		jbStartStop.addActionListener(this);

		jpNorth.add(jbStartStop);
		add(jpNorth, BorderLayout.NORTH);

		// CENTER
		// Holds eventLog - displays messages, errors, and connections
		JPanel jpCenter = new JPanel();
		eventLog = new JTextArea(20,45);
		eventLog.setEditable(false);
        eventLog.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(eventLog);
		jpCenter.add(scroll);
		add(jpCenter);

		setTitle("Server");
		setSize(600, 400);
        addWindowListener(this); // safely stop server when GUI is closed
		setVisible(true);
	}		

	/*
	 * Receives the full message from the UDPServer and 
	 * is then sent to the full Server for broadcast to full list of clients
	 */
	public void serverBroadcast(String message){
		server.broadcast(message);
	}
	/*
	 * Display message/event in eventLog
	 */
	public void displayEvent(String msg) {
		eventLog.append(msg + "\n");
	}

	/*
	 * If window closed, stop Server and exit
	 */
	public void windowClosing(WindowEvent e) {
		if(server != null) {
			try {
				server.stop();
			} catch(Exception eClose) {}
			server = null;
		}
		if(udpServer != null) {
			try {
				udpServer.stop();
			} catch(Exception eClose) {}
			server = null;
		}
		System.exit(0);
	}
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

	/*
	 * A thread to run the Server
	 */
	class RunServer extends Thread {
		public void run() {
		    // start Server
			server.start();
			
			// the server failed
            displayEvent("Server stopped");
			jbStartStop.setText("Start Server");
			tfPort.setEditable(true);
			server = null;
			udpServer = null;
		}
	}
	
	/* 
	 * A thread to run the UDP Server
	 */
	class RunUDPServer extends Thread { 
		public void run(){
			udpServer.start();
		}
	
	}

	/*
	 * jbStartStop was clicked, so start/stop Server
	 */
	public void actionPerformed(ActionEvent e){
        // if Server already running, stop it
        if(server != null) {
            server.stop();
            server = null;
			udpServer.stop();
			udpServer = null;
            tfPort.setEditable(true);
            jbStartStop.setText("Start Server");
            return;
        }
        // otherwise start Server
        int port;
        try {
            port = Integer.parseInt(tfPort.getText().trim());
        } catch(Exception ex) {
            displayEvent("Invalid port");
            return;
        }
        // create and start new Server and new UDP Server
        server = new TCPServer(port, this);
		udpServer = new UDPServer(port, this);
		new RunUDPServer().start();
        new RunServer().start();
		
        jbStartStop.setText("Stop Server");
        tfPort.setEditable(false);
    }
	
	

    /*
     * Main class, creates ServerGUI (which creates Server)
     */
    public static void main(String[] arg) {
        ServerGUI gui = new ServerGUI();
    }

}