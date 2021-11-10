import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ChatClient {

    public static final int N = 5;

    String serverIp;
    int serverPort;
    ListenClient listenClient;
    SendMessages sendMessages;

    ChatClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }
    public static void main(String[] args) {
        new ChatClient("localhost", 8080).start();
    }


    public void start() {
        Socket socket = connect();
        if (socket != null) {
            // create and start threads
            this.listenClient = new ListenClient(socket, this);
            this.listenClient.start();

            this.sendMessages = new SendMessages(socket);
            this.sendMessages.start();
        }
    }

    public Socket connect() {
        for (int i=0; i < N; i++){
            // sleep for 1 sec
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // try to connect again
            try{
                return new Socket(this.serverIp, this.serverPort);
            } catch (IOException e) {
                System.out.println("Can't connect to server: " + i);
            }
        }
        System.out.println("Bye");
        return null;
    }

}
