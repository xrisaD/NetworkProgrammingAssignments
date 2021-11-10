import java.io.*;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {
        var chatClient = new ChatClient();
        chatClient.start();
    }

    public void start() {
        try {
            var connection = new Socket("localhost", 8000);
            new InputThread(connection).start();
            new OutputThread(connection).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class InputThread extends Thread {
    private InputStream in;

    public InputThread(Socket connection) {
        try {
            in = connection.getInputStream();
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class OutputThread extends Thread {
    private OutputStream out;

    public OutputThread(Socket connection) {
        try {
            out = connection.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            var dispatcher = new PrintStream(out);
            var reader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while ((msg = reader.readLine()) != null) {
                dispatcher.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}