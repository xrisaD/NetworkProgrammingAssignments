import java.util.Hashtable;

public class HTMLFileDocumentWithParams extends HTMLFileDocument{
    Hashtable<String, String> parameters;

    public HTMLFileDocumentWithParams(String name) {
        super(name);
    }

    public void setParameters(Hashtable<String, String> parameters) {
        this.parameters = parameters;
    }

    public void load() {
        super.load();
        if (this.parameters != null) {
            for (String key : parameters.keySet()) {
                if (content.contains("{" + key + "}")) {
                    content = content.replace("{" + key + "}", parameters.get(key));
                }
            }
        }
    }
}
