import javax.swing.*;


/*
 * Prompts the user for input using a simple gui. User input is used to create the specific kind of client required.
 */
public class ClientStart extends JFrame{
	private String host, username;
	private int port;
	private String protocol;
	private boolean isConnected = true;
	private String curProtocol;
	
	public static void main(String[] args) {
		ClientStart clientStart = new ClientStart();
	}
	//Constructor simply calls the prompt method to begin accepting user input
	ClientStart(){
		prompt();
	}
	public void prompt(){
		
		//Prompt user for input
        JTextField inputName = new JTextField();
		String[] protocols = {"TCP", "UDP"};
        JComboBox<String> protocolList = new JComboBox<String>(protocols);
		protocolList.setEditable(false);
        JTextField inputHost = new JTextField();
        JTextField inputPort = new JTextField("1678");
        Object[] inputMessages = {
                "Enter your name:", inputName,
                "Enter host/IP:", inputHost,
				"Choose protocol", protocolList,
                "Enter port:", inputPort
        };
        int option = JOptionPane.showConfirmDialog(null, inputMessages, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            // check valid username input
            if(inputName.getText().trim().length() == 0){
                this.username = "Anonymous";
            } else {
                this.username = inputName.getText().trim();
            }
            // check valid protocol input
			try{
				curProtocol = (String) protocolList.getSelectedItem();
			}catch(Exception e){
				System.out.println("Bad things");
			}
            if(curProtocol.equals("TCP")){
                this.protocol = "TCP";
            } else {
                this.protocol = "UDP";	
            }
            // check valid host input
            if(inputHost.getText().trim().length() == 0){
                this.host = "localhost";
            } else {
                this.host = inputHost.getText().trim();
            }
            // check not empty port input
            if(inputPort.getText().trim().length() != 0){
                // try to parse port input to int
                try {
                    this.port = Integer.parseInt(inputPort.getText().trim());
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            } else {
                this.port = 1678; // set to default port
            }
        }
		// determines what client to create using the user input and the protocol value.
		if(this.protocol.equals("TCP")){
			TCPClient client = new TCPClient(this.host, this.port, this.username);
			if(client.start()){
				isConnected = true;
			}
		}else{
			UDPClient client = new UDPClient(this.host,  this.port, this.username);
			client.start();
		}
	}
}
	

	