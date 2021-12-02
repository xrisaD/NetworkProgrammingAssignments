import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

public class RetrieveEmail {

    public static void main(String[] args) throws IOException {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        HttpsURLConnection.setDefaultSSLSocketFactory(sf);
        SSLSocket socket = null;
        String host = "webmail.kth.se";
        int port = 993;

        try {
            socket = (SSLSocket) sf.createSocket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            socket.startHandshake();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // login to my account
        writer.println("a1 LOGIN cdik " + args[0]);
        writer.flush();

        // contents
        writer.println("a2 EXAMINE inbox");
        writer.flush();

        // fetch the first e-mail
        writer.println("a3 FETCH 1 FULL");
        writer.flush();

        // print all the responses
        String line;
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }

}
