package models;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private String text;
    private String[] options;
    private int correct;
    private int pos;
    private List<Selector> selectors;

    public Question() {
    }

    public Question(String text, String[] options, int correct){
        this.text = text;
        this.options = options;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public List<Selector> getSelectors() {
        return selectors;
    }

    public void setSelectors(List<Selector> selectors) {
        this.selectors = selectors;
    }
}
