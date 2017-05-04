import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * ClientGUI
 */
public class ClientGUI extends JFrame {

	// to hold the Username and later on the messages
	private JTextField tfMessage;
	// to display messages
	private JTextArea chatRoom;
	private boolean isConnected = true;
	private Client client;

	// Constructor connection receiving a socket number
	ClientGUI(Client newClient) {
        this.client = newClient;

        // NORTH
        // Create JButton to disconnect from server and quit program
        JPanel jpNorth = new JPanel();
        jpNorth.setPreferredSize(new Dimension(500, 50));
        JButton jbDisconnect = new JButton("Disconnect");
        jbDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // send disconnect Message when clicked
                client.sendMessage(new Message(Message.DISCONNECT, null));
                client.disconnect();
                System.exit(0);
            }
        });
        // Add JButton to JPanel
        jpNorth.add(jbDisconnect);
        // Add JPanel to JFrame
        add(jpNorth,BorderLayout.NORTH);

        // SOUTH
        // JPanel for border south
        JPanel jpSouth = new JPanel();

        // Create JTextField for user inputs and add it to south panel
        tfMessage = new JTextField(20);
        jpSouth.add(tfMessage);

        // Create JButton for sending messages and add it to south panel
        jpSouth.setPreferredSize(new Dimension(500, 50));
        JButton jbSend = new JButton("Send");
        // Add action listener to get client message
        jbSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isConnected) {
                    // send message
                    client.sendMessage(new Message(Message.MESSAGE, tfMessage.getText()));
                    tfMessage.setText("");
                }
            }
        });
        jpSouth.add(jbSend);

        // Add send text field and button panel to JFrame
        add(jpSouth,BorderLayout.SOUTH);

        // Create JPanel
        JPanel jpClient = new JPanel();
        chatRoom = new JTextArea(20, 45);
        chatRoom.setEditable(false); // make it read-only
        chatRoom.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(chatRoom);
        jpClient.add(scroll);

        // Add text area panel to JFrame
        add(jpClient);

        // Set JFrame property
        setTitle("Client");
        setLocation(380, 150);
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}

	// Add message to GUI's chatRoom
	public void displayMessage(String str) {
		chatRoom.append(str + "\n");
	}

	// called by the Client if the connection failed
	public void failedToConnect() {
		isConnected = false;
	}
}