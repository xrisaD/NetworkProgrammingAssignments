import java.util.ArrayList;
import java.util.Hashtable;

public class HTMLDocuments {
    public Hashtable<String, HTMLDocument> documents = new Hashtable<>();
    public ArrayList<HTMLDocument> listWithDocuments = new ArrayList<>();

    public void loadDocuments (ArrayList<HTMLDocument> htmlDocuments) {
        for (HTMLDocument document: htmlDocuments) {
            document.load();
        }
    }

    public ArrayList<HTMLDocument> getListWithDocuments() {
        return listWithDocuments;
    }

    public Hashtable<String, HTMLDocument> getDocuments() {
        return documents;
    }
}
