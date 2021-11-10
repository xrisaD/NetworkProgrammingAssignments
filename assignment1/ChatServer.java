import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    public static List<Socket> clients = new ArrayList<>();

    public static void broadcast(String message) {
        for (Socket client: clients) {
            try {
                var out = new PrintStream(client.getOutputStream());
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void startServer(){
        // server is listening for clients
        ListenServer listenServer = new ListenServer();
        listenServer.start();
    }

    public static void main(String[] args) {
        new ChatServer().startServer();
    }
}
