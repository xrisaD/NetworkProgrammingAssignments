import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ListenClient extends Thread {

    private Socket socket = null;
    private ChatClient chatClient = null;

    public ListenClient(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        System.out.println("Listen client started");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (SocketException e) {
            System.out.println("Server crashed");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
