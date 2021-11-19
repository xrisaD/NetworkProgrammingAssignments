package models;

import java.io.Serializable;

public class Selector implements Serializable {
    private Quiz quiz;
    private Question question;

    public Selector() {
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
