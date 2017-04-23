import java.net.*;
import java.io.*;

/* Server for client / server network communication
*/

public class Server{
    public static void main(String [] args) {
        String clientMsg;
        ServerSocket ss = null;
        BufferedReader br;
		BufferedReader initial;
        PrintWriter opw;
		try { 
			System.out.print("Please enter a protocol: ");
			initial = new BufferedReader(new InputStreamReader(System.in));
			String protocol = initial.readLine();
			if(protocol.equals("UDP")){
				try{
					DatagramSocket s = new DatagramSocket(16789);
					while(true){
						System.out.println("Waiting for packets....");
						DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
						s.receive(packet);
						String message = new String(packet.getData());
						message = message.trim();
						System.out.println("Message from: " + packet.getAddress().getHostName()+ "-" + message);
					}
				}
				catch ( Exception e){
					System.out.println ( "ServerException");
				}
			}
			else if(protocol.equals("TCP/IP")){
				try {
					System.out.println("getLocalHost: "+InetAddress.getLocalHost() );
					System.out.println("getByName:    "+InetAddress.getByName("localhost") );
					ss = new ServerSocket(16789);
					Socket cs = null;
					while(true){ 		// run forever once up
						try{
							System.out.println("Waiting for connection");
							cs = ss.accept();  // wait for connection
							
							br = new BufferedReader(
									new InputStreamReader(
											cs.getInputStream()));
							opw = new PrintWriter(
									new OutputStreamWriter(
											cs.getOutputStream()));

							clientMsg = br.readLine();					// from client
							System.out.println("Server read: "+ clientMsg);
							opw.println(clientMsg.toUpperCase());	//to client
							opw.flush();
						}
						catch( IOException e ) {
							System.out.println("Inside catch");
							e.printStackTrace();
						}
					} // end while
				}
				
				catch( BindException be ) {		// I/O exception catches this
					System.out.println("Server is already running. This server stopping.");
				}
				catch( IOException e ) {
					System.out.println("Outside catch");
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			System.out.println("Error number 1");
		}
    }
}
