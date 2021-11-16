import java.io.*;
import java.net.*;
import java.util.*;

public class HttpServer{

	public static int sessionCounter = 1;
	public static Hashtable<Integer, Session> sessionList = new Hashtable<>();
	public static HTMLDocuments htmlDocuments = new HTMLDocuments();

	public static void createDocuments() {
		// add documents manually here
		htmlDocuments.documents.put("/index.html", new HTMLFileDocument("/index.html"));
		htmlDocuments.documents.put("/guess.html", new HTMLFileDocumentWithParams("/guess.html"));
		htmlDocuments.documents.put("/error.html", new HTMLFileDocument("/error.html"));
		htmlDocuments.documents.put("/success.html", new HTMLFileDocumentWithParams("/success.html"));
	}

    public static void main(String[] args) {
		createDocuments();
		try {
			startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void startServer() throws IOException {
		ServerSocket ss = new ServerSocket(8080);
		while(true){
			System.out.println("Waiting for client...");
			Socket s = ss.accept();
			System.out.println("Client connected");
			BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			Request request = passRequest(reader);
			s.shutdownInput();

			createReply(s, request);
		}
	}
	public static Request passRequest(BufferedReader request) throws IOException {
		// Request line
		String str = request.readLine();
		System.out.println(str);
		StringTokenizer tokens = new StringTokenizer(str," ?");
		String method = tokens.nextToken(); // The word GET, POST etc
		String requestedDocument = tokens.nextToken(); // path of the requested document


		String argumentOrHTTP = tokens.nextToken();
		Hashtable<String, String> variables = null;
		// if it contains "=", then we have arguments
		if (argumentOrHTTP.contains("=")) {
			variables = new Hashtable();
			StringTokenizer argumentTokens = new StringTokenizer(argumentOrHTTP,"=;");
			System.out.println(argumentOrHTTP);
			while (argumentTokens.hasMoreElements()) {
				String name = argumentTokens.nextToken(); // get the name of the variable
				String value = argumentTokens.nextToken(); // get the value of the variable
				variables.put(name, value);
			}
		}

		// Find Cookie at the headers
		Boolean cookieFound = false;
		Session session = null;
		while( (str = request.readLine()) != null && str.length() > 0){
			System.out.println(str);
			if (str.startsWith("Cookie")){
				tokens = new StringTokenizer(str,"=");
				tokens.nextToken(); //skip all before =
				int sessionId = Integer.parseInt(tokens.nextToken());
				// get session
				if (sessionList.get(sessionId) != null) {
					session = sessionList.get(sessionId);
					cookieFound = true;
				} else {
					cookieFound = false;
				}

			}
		}
		// initiate new session if necessary
		if (!cookieFound) {
			// create session and save it
			session = new Session(sessionCounter);
			sessionList.put(sessionCounter, session);
			sessionCounter++;
		}

		System.out.println("Request processed.");
		return new Request(method, requestedDocument, variables, session);
	}

	public static void createReply(Socket s, Request request) throws IOException {
		System.out.println("Sent reply");
		PrintStream response = new PrintStream(s.getOutputStream());
		response.println("HTTP/1.1 200 OK");
		response.println("Server: Trash 0.1 Beta");

		if (request.getRequestedDocument().contains(".html")) {
			response.println("Content-Type: text/html");
		}
		if(request.getRequestedDocument().contains(".gif")) {
			response.println("Content-Type: image/gif");
		}

		response.println("Set-Cookie: clientId=" + request.getSession().getSessionId());

		// empty line
		response.println();

		if(!"/favicon.ico".equals(request.requestedDocument)){ // Ignore any additional request to retrieve the bookmark-icon.
			System.out.println("TARGET: "+request.session.getGuessGame().target);
			HTMLDocument htmlDocument = null;
			if (htmlDocuments.getDocuments().get(request.requestedDocument)!=null) {
				// load params if needed
				if (Objects.equals(request.getRequestedDocument(), "/guess.html")) {
					Hashtable<String, String> variables = request.getVariables();

					if (variables !=null && variables.get("value") != null){
						String guess = variables.get("value");
						Hashtable<String, String> params = new Hashtable<>();
						request.session.increaseTries();
						params.put("numOfGuesses", String.valueOf(request.session.getTries()));

						int num = request.session.getGuessGame().checkGuess(Integer.parseInt(guess));

						if (num == 0) {
							// serve success
							htmlDocument = htmlDocuments.getDocuments().get("/success.html");
						} else if (num > 0) {
							htmlDocument = htmlDocuments.getDocuments().get("/guess.html");
							params.put("higherOrLower", "lower");
						} else {
							htmlDocument = htmlDocuments.getDocuments().get("/guess.html");
							params.put("higherOrLower", "higher");
						}
						((HTMLFileDocumentWithParams) htmlDocument).setParameters(params);
					} else {
						// error
						htmlDocument = htmlDocuments.getDocuments().get("/error.html");
					}
				} else {
					htmlDocument = htmlDocuments.getDocuments().get(request.requestedDocument);
				}

				htmlDocument.load();

				// start a new game
				if (htmlDocument.name.equals("/success.html")) {
					request.getSession().newGame();
				}
				// send content
				response.println(htmlDocument.content);
			}
			s.shutdownOutput();
			s.close();
			System.out.println();
		}
	}
}