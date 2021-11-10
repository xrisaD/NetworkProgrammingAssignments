import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    public static List<Socket> clients = new ArrayList<>();

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.startServer();
    }

    public void startServer() {
        System.out.println("Server started.");
        try {
            ServerSocket server = new ServerSocket(8000);
            while (true) {
                var clientSocket = server.accept();
                clients.add(clientSocket);
                new ClientThread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

}

class ClientThread extends Thread {
    private InputStream in;

    public ClientThread(Socket clientSocket) {
        try {
            in = clientSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        var reader = new BufferedReader(new InputStreamReader(this.in));
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
                ChatServer.broadcast(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}