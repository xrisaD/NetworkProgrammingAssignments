import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

public class SendEmail {

    public static void main(String[] args) throws IOException {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        HttpsURLConnection.setDefaultSSLSocketFactory(sf);

        String host = "smtp.kth.se";
        int port = 587;
        String from = "cdik@kth.se";
        String to = from;

        // socket
        Socket socket = new Socket(host, port);

        PrintWriter writer = null;
        BufferedReader reader = null;

        writer = new PrintWriter(socket.getOutputStream());
        reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));

        writer.println("HELO " + host);
        writer.flush();
        System.out.println(reader.readLine());

        writer.println("STARTTLS");
        writer.flush();
        System.out.println(reader.readLine());
        System.out.println(reader.readLine());
        // SSLSocket
        SSLSocket sslSocket = null;

        sslSocket = (SSLSocket) sf.createSocket(socket, socket.getInetAddress().getHostAddress(),
                    socket.getPort(),
                    true);

        writer = new PrintWriter(sslSocket.getOutputStream());
        reader = new BufferedReader( new InputStreamReader(sslSocket.getInputStream()));

        writer.println("HELO " + host);
        writer.flush();
        System.out.println(reader.readLine());

        writer.println("AUTH LOGIN");
        writer.flush();
        System.out.println(reader.readLine());

        writer.println(new String(Base64.getEncoder().encode(("cdik").getBytes())));
        writer.flush();
        System.out.println(reader.readLine());

        writer.println(new String(Base64.getEncoder().encode(args[0].getBytes())));
        writer.flush();
        System.out.println(reader.readLine());

        writer.println("MAIL From:<" + from + ">");
        writer.flush();
        System.out.println(reader.readLine());

        writer.println("RCPT TO:<" + to + ">");
        writer.flush();
        System.out.println(reader.readLine());

        writer.println("DATA");
        writer.flush();
        System.out.println(reader.readLine());

        writer.println("Message");
        writer.println(".");
        writer.flush();

        writer.println("QUIT");
        writer.flush();

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

    }
}
