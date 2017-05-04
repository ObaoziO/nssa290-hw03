import java.io.*;

/*
 * Message
 * Used to define what type of message is being sent
 */
public class Message implements Serializable {

    // MESSAGE is normal message
	static final int MESSAGE = 0;
    // DISCONNECT is client trying to disconnect
    static final int DISCONNECT = 1;
	// USERNAME is sent when client tries to connect
	static final int USERNAME = 2;
	private int type;
    private String content;
	
	// Constructor
	Message(int type, String content) {
		this.type = type;
		this.content = content;
	}

	int getType() {
		return type;
	}

	String getContent() {
		return content;
	}
}