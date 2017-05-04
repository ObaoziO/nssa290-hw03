import java.net.*;
import java.io.*;


public class UDPClient implements Client {
	
	private DatagramSocket dSocket;
	private ClientGUI gui;
	// the host server, the port, and the username
	private String host, username;
	private int port;
	private String protocol;

	/*
	 * Constructor
	 */
	UDPClient(String host, int port, String username) {
		this.host = host;
		this.port = port;
		this.username = username;
		
	}
	
	/*
	 * Starts the server and creates Datagram Socket so the messages can be sent.
	 */
	public boolean start() {
		
		this.gui = new ClientGUI(this);
		
		try { 
			dSocket = new DatagramSocket();
		}catch (Exception e) {
			gui.displayMessage("Error");
			return false;
		}
		
		String msg = "Please enter your message....";
		gui.displayMessage(msg);
		return true;
		
		
	}
	
	/*
	 * Sends message in the form of a packet to the UDP Server. 
	 */
	public void sendMessage(Message msg) {
		
		try {
			if(msg.getType() == 0){
				
				String message = msg.getContent();
				byte[] data = message.getBytes();
				InetAddress addr = InetAddress.getByName(host);
				DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
				
				
				try{
					// Displays message on the current users gui.
					gui.displayMessage("Sending....");
					gui.displayMessage(username + ": " + message);
					dSocket.send(pack);
				}catch(PortUnreachableException e){
					gui.displayMessage("Exception2");
				}
				//if message type = disconnect, then disconnect.
			}else if(msg.getType() == 1){
				String message = (username + " disconnected");
				byte[] data = message.getBytes();
				InetAddress addr = InetAddress.getByName(host);
				DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
				try{
					
					dSocket.send(pack);
				}catch(PortUnreachableException e){
					gui.displayMessage("Exception2");
				}
				disconnect();
			}else if (msg.getType() == 2){
				String message = (username + " connected using UDP Protocol");
				byte[] data = message.getBytes();
				InetAddress addr = InetAddress.getByName(host);
				DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
				try{
					
					dSocket.send(pack);
				}catch(PortUnreachableException e){
					gui.displayMessage("Exception2");
				}
			}
		}
		catch(IOException e) {
			gui.displayMessage("Exception writing to server: " + e);
		}
	}
	
	/*
	 * Closes Datagram Socket.
	 */
	public void disconnect() {
		gui.displayMessage("Disconnecting");
		dSocket.close();
	}


}