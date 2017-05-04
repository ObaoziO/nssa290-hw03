import java.io.*;
import java.net.*;


public class UDPServer {
	//global variables
	private ServerGUI gui;
	private int port;
	private boolean isRunning;
	private DatagramSocket ds;
	private DatagramPacket packet;
	private InetAddress ip;
	
	/*
	 * Constructor, takes a port and the ServerGUI that created it
	 */
	public UDPServer(int port, ServerGUI gui){
		this.port = port;
		this.gui = gui;
		try{
			this.ip = InetAddress.getLocalHost();
		}catch(Exception e){
			System.out.println("Error getting address");
		}
	}
	
	/*
	 * Starts the UDPServer by creating a Datagram socket to accept the packets. Trims the message, converts it to final format and then sends to the ServerGUI for broadcast
	 */
	public void start() {
		isRunning = true;
		
		try{
            ds = new DatagramSocket(null);
            ds.setReuseAddress(true);
            ds.bind(new InetSocketAddress(ip, port));
            while(isRunning){
                packet = new DatagramPacket(new byte[1024], 1024);
                try{
                    // Receive packet and then format message for final output.
                    ds.receive(packet);
                    String message = new String (packet.getData());
                    message = message.trim();
                    message = ("Message from: " + packet.getAddress() + " Using UDP Protocol - " + message);
                    gui.serverBroadcast(message);
                }catch(IOException e){
                    System.out.println(e);
                }
						
            }
				// try and close after while loop ends
				try{
					ds.close();
				}catch(Exception e){
					System.out.println("Error closing");
				}
		
		}catch(SocketException e){
			System.out.println(e);
		}
	}
	
	/*
	 * stop running the server
	 */
	public void stop() {
		isRunning = false;
		try {
			new DatagramSocket(port);
		} catch(Exception e) {}
	}
	
	
	

}