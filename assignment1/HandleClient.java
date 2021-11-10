import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class HandleClient extends Thread{
    Socket socket = null;

    public HandleClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Handle client started");
        try {
            var reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
                ChatServer.broadcast(message);
            }
        } catch (SocketException e) {
            System.out.println("Client crashed");
            // remove the client
            ChatServer.clients.remove(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
