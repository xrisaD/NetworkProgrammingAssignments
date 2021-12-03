import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HTMLFileDocument extends HTMLDocument{
    public HTMLFileDocument(String name) {
        super(name);
    }

    public void load() {
        super.load();
        try {
            File myObj = new File("assignment2/resources" + this.name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                this.content += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
