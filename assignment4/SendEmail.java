import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

public class SendEmail {

    public static void main(String[] args) throws IOException {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        HttpsURLConnection.setDefaultSSLSocketFactory(sf);
        SSLSocket socket = null;
        String host = "smtp.kth.se";
        int port = 587;

        try {
            socket = (SSLSocket) sf.createSocket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String[] c = {"TLS_AES_256_GCM_SHA384"};
//        socket.setEnabledCipherSuites(c);

//        for (String i: socket.getEnabledCipherSuites()) {
//            System.out.println(i);
//        }

        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str;

        // send request
        writer.println("HELO\r\n");
        writer.println("MAIL FROM:<mail@samlogic.com>");
        System.out.println("Request Send");

//        if ((str=reader.readLine())!=null) {
//            System.out.println(str);
//        }

        // send request
        writer.println("a001 login");
        System.out.println("Request Send");
        if ((str=reader.readLine())!=null) {
            System.out.println(str);
        }

    }
}
