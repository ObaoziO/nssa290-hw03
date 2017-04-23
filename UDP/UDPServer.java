import java.io.*;
import java.net.*;

public class UDPServer{

	public static void main(String args[]) throws Exception{
	
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
}

