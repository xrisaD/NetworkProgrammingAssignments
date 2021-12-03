import java.util.Hashtable;

public class Request {
    String method;
    String requestedDocument;
    Hashtable<String, String> variables;
    Session session;

    public Request(String method, String requestedDocument, Hashtable<String, String> variables, Session session) {
        this.method = method;
        this.requestedDocument = requestedDocument;
        this.variables = variables;
        this.session = session;
    }



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestedDocument() {
        return requestedDocument;
    }

    public void setRequestedDocument(String requestedDocument) {
        this.requestedDocument = requestedDocument;
    }

    public Hashtable<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Hashtable<String, String> variables) {
        this.variables = variables;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
