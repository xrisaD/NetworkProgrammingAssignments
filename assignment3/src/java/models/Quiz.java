package models;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
    private String subject;
    private List<Result> results;
    private List<Selector> selectors;

    public Quiz() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Selector> getSelectors() {
        return selectors;
    }

    public void setSelectors(List<Selector> selectors) {
        this.selectors = selectors;
    }
}
