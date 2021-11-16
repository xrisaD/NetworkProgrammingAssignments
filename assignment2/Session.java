import java.util.ArrayList;
import java.util.List;

public class Session {
    private int tries;
    private int sessionId;
    private GuessGame guessGame;
    private List<GuessGame> games = new ArrayList<>();

    public Session(int sessionId) {
        this.tries = 0;
        this.sessionId = sessionId;
        this.guessGame = new GuessGame();
    }

    public void increaseTries() {
        this.tries = this.tries + 1;
    }
    public Integer getSessionId(){
        return sessionId;
    }

    public GuessGame getGuessGame() {
        return guessGame;
    }

    public int getTries() {
        return tries;
    }

    public void newGame () {
        this.games.add(this.guessGame);
        this.guessGame = new GuessGame();
        this.tries = 0;
    }
}