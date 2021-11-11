
import java.io.*;
import java.net.*;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;

public class HttpServer{

	public static int sessionCounter = 1;
	public static Hashtable<Integer, Session> sessionList = new Hashtable<>();

    public static void main(String[] args) throws IOException{
	System.out.println("Creating ServerSocket");
	ServerSocket ss = new ServerSocket(8080);
	while(true){
	    System.out.println("Waiting for client...");
	    Socket s = ss.accept();
	    System.out.println("Client connected");
	    BufferedReader request =
		new BufferedReader(new InputStreamReader(s.getInputStream()));
	    String str = request.readLine();
	    System.out.println(str);
	    StringTokenizer tokens = new StringTokenizer(str," ?");
	    tokens.nextToken(); // The word GET
	    String requestedDocument = tokens.nextToken(); //path
		String arguments = tokens.nextToken(); //get guessed value
		Boolean cookieFound = false;
		Integer sessionId;
	    while( (str = request.readLine()) != null && str.length() > 0){
			System.out.println(str);
			if (str.startsWith("Cookie")){
				tokens = new StringTokenizer(str,"=");
				tokens.nextToken(); //skip all before =
				sessionId = Integer.valueOf(tokens.nextToken());
				cookieFound = true;
			}
	    }
		//initiate new session if necessary
		if (!cookieFound) {
			sessionId = sessionCounter;
			sessionCounter++;

		}
	    System.out.println("Request processed.");
	    s.shutdownInput();
	    
	    PrintStream response =
		new PrintStream(s.getOutputStream());
	    response.println("HTTP/1.1 200 OK");
	    response.println("Server: Trash 0.1 Beta");
	    if(requestedDocument.indexOf(".html") != -1)
		response.println("Content-Type: text/html");
	    if(requestedDocument.indexOf(".gif") != -1)
		response.println("Content-Type: image/gif");
	    //Use Cookie if existing from previous session
	    response.println("Set-Cookie: clientId=1");

	    response.println();
            if(!"\favicon.ico".equals(requestedDocument)){ // Ignore any additional request to retrieve the bookmark-icon.
                File f = new File("assignment2/resources"+requestedDocument);
                FileInputStream fileIn = new FileInputStream(f);
                byte[] b = new byte[1024];
                while( fileIn.available() > 0){
                    response.write(b,0,fileIn.read(b));
                }
                s.shutdownOutput();
                s.close();
            }
        }
    }
}

class Session{

	int tries;
	int sessionId;
	GuessGame guessGame;

	public Session() {
		tries = 0;
		sessionId = HttpServer.sessionCounter; //stupid and unsafe on purpose
		HttpServer.sessionCounter++;
		HttpServer.sessionList.put(sessionId, this); //refactor with private and method
		this.guessGame = new GuessGame();
	}

	public Integer getSessionId(){
		return sessionId;
	}
}

class GuessGame{
	int target;

	public GuessGame() {
		Random rand = new Random();
		this.target = rand.nextInt(99) + 1;
	}

	public Integer checkGuess(Integer guess){
		String result = "";
		return Integer.compare(guess, target);

	}
}