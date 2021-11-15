public abstract class HTMLDocument {
    String name;
    String content;

    public HTMLDocument(String name) {
        this.name = name;
    }

    public void load() {
        this.content = "";
    }

    @Override
    public String toString() {
        return "HTMLDocument{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
