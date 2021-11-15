
public class Session {
    private int tries;
    private int sessionId;
    private GuessGame guessGame;

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
}