import java.io.*;
import java.net.*;


public class UDPClient {

	public static void main(String args[]) throws Exception{
		String message;
		BufferedReader br = null;
		String exit = "Exit";
		String exit2 = "exit";
		
		int port = 16789;
		try{	
			while(true){
				System.out.print("Please enter your message: ");
				br = new BufferedReader(new InputStreamReader(System.in));
				message = br.readLine();
				DatagramSocket ds = new DatagramSocket();
				if(message.equals(exit) || message.equals(exit2)){
					ds.close();
					return;
				}
				byte[] data = message.getBytes();
				InetAddress addr = InetAddress.getByName("192.168.1.127");
				DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
				ds.send(pack);
			}
		}
		catch ( Exception e ) {
			System.out.println("An Unspecified Error Has Occured");
		}
		
			
	}
	
}