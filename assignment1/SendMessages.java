import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SendMessages extends Thread{

    Socket socket;

    public SendMessages(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintStream dispatcher = new PrintStream(this.socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while ((msg = reader.readLine()) != null) {
                dispatcher.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
