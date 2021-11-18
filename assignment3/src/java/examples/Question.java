package examples;

public class Question {

    private String text;
    private String[] options;
    private int correct;
    private int pos;
    
    public Question(String text, String[] options, int correct){
        this.text = text;
        this.options = options;
        this.correct = correct;
    }
}
