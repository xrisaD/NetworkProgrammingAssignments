import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenServer extends Thread {
    @Override
    public void run() {
        try {
            ServerSocket providerSocket = new ServerSocket(8080);

            while (true) {
                // accept
                Socket connection = providerSocket.accept();
                System.out.println("New connection");
                // create a new thread which will handle the connection with this client
                HandleClient handleClient = new HandleClient(connection);
                handleClient.start();

                ChatServer.clients.add(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
