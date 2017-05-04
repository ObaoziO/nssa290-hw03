import java.io.*;
import java.net.*;
import java.util.*;

/*
 * TCPServer
 */
public class TCPServer {
	// need a unique ID for each Client
	private static int clientID;
	// list of Clients
	private ArrayList<ClientThread> clientList;
	// if I am in a GUI
	private ServerGUI gui;
	// port number to listen on
	private int port;
	// change this to false to stop server
	private boolean isRunning;
	private String ip;

	// Constructor
	public TCPServer(int port, ServerGUI gui) {
		this.gui = gui;
		this.port = port;
		clientList = new ArrayList<>();
		// Try to get Server's IP address for TCP Clients
		try {
			this.ip = InetAddress.getLocalHost().getHostAddress();
		} catch(UnknownHostException e) {
			this.ip = "localhost";
			gui.displayEvent("Unknown host exception: " + e);
		}
	}

	/*
	 * Start Server
	 */
	public void start() {
		isRunning = true;
		try {
			// create new ServerSocket using port
			ServerSocket serverSocket = new ServerSocket(port);

			// wait for Clients to connect
			while(isRunning) {
				// format message saying we are waiting
				gui.displayEvent("Server " + ip + " listening for clients on port " + port);
				// accept client connections
				Socket socket = serverSocket.accept();
				// check if server should stop
				if(!isRunning) {
					break;
				}
				ClientThread ct = new ClientThread(socket);
				// add new client to clientList
				clientList.add(ct);
				ct.start();
			}
			// try to stop
			try {
				serverSocket.close();
				for(int i = 0; i < clientList.size(); ++i) {
					ClientThread ct = clientList.get(i);
					try {
						ct.sInput.close();
						ct.sOutput.close();
						ct.socket.close();
					}
					catch(IOException ioE) {}
				}
			} catch(Exception e) {
				gui.displayEvent("Exception closing the server and clients: " + e);
			}
		} catch (IOException e) {
			gui.displayEvent("Exception on ServerSocket: " + e);
		}
	}

	/*
     * Stop the Server
     */
	public void stop() {
		isRunning = false;
		try {
			new Socket("localhost", port);
		} catch(Exception e) {}
	}

	/*
	 * Broadcast a message to all Clients
	 */
	public synchronized void broadcast(String message) {
		// display message in ServerGUI
		gui.displayEvent(message);

		// Loop through clients, remove disconnected ones
		for(int i = clientList.size(); --i >= 0;) {
			ClientThread ct = clientList.get(i);
			// try to send message to all Clients, if it fails remove Client from clientList
			if(!ct.sendMessage(message)) {
				clientList.remove(i);
				gui.displayEvent(ct.username + " disconnected");
			}
		}
	}

	/*
	 * Remove Client from clientList when they disconnect
	 */
	private synchronized void removeClient(int id) {
		for(int i = 0; i < clientList.size(); ++i) {
			ClientThread ct = clientList.get(i);
			if(ct.id == id) {
				clientList.remove(i);
				return;
			}
		}
	}

	/*
	 * Create thread for each Client
	 */
	public class ClientThread extends Thread {
		int id;
		String username;
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		// the type of message received (DISCONNECT/MESSAGE/USERNAME)
		Message msgObj;

		// Constructor
		ClientThread(Socket socket) {
			// create unique id for each Client
			id = ++clientID;
			this.socket = socket;
			try {
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput  = new ObjectInputStream(socket.getInputStream());
				// read Client's username
				msgObj = (Message) sInput.readObject();
				username = msgObj.getContent();
				broadcast(username + " connected using TCP/IP");
			} catch (IOException e) {
				gui.displayEvent("Exception IO: " + e);
				return;
			} catch (ClassNotFoundException e) {
				gui.displayEvent("Exception ClassNotFound: " + e);
			}
		}

		/*
		 * Run Server
		 */
		public void run() {
			boolean isRunning = true;
			while(isRunning) { // loop until stopped
				// read a String (which is an object)
				try {
					msgObj = (Message) sInput.readObject();
				} catch (IOException e) {
					gui.displayEvent(username + " Exception reading Streams: " + e);
					break;
				} catch(ClassNotFoundException e2) {
					break;
				}
				String message = msgObj.getContent();

				// find type of message
				// if disconnect, disconnect
				if(msgObj.getType() == Message.DISCONNECT){
					broadcast(username + " disconnected");
					isRunning = false;
				} else { // normal message, so broadcast it
					broadcast(username + ": " + message);
				}
			}
			// remove this Client from clientList
			removeClient(id);
			close();
		}

		/*
         * Try to close connection
         */
		private void close() {
			// try to close the connection
			try {
				if(sOutput != null) sOutput.close();
			} catch(Exception e) {}
			try {
				if(sInput != null) sInput.close();
			} catch(Exception e) {}
			try {
				if(socket != null) socket.close();
			} catch (Exception e) {}
		}

		/*
		 * Send a message
		 * Used by broadcast to send a message to each client
		 */
		private boolean sendMessage(String msg) {
			// if Client is still connected, send the message to it
			if(!socket.isConnected()) {
				close();
				return false;
			}
			// write the message to the stream
			try {
				sOutput.writeObject(msg);
			}
			// inform Client that message did not send
			catch(IOException e) {
				gui.displayEvent("Error sending message to " + username);
				gui.displayEvent(e.toString());
			}
			return true;
		}
	}
}