/**
 * Defines common methods for the two client types
 */
public interface Client {
    void sendMessage(Message msg);
    void disconnect();
    boolean start();
}
