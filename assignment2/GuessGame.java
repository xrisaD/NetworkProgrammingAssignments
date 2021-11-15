import java.util.Random;

public class GuessGame{
    int target;

    public GuessGame() {
        Random rand = new Random();
        // generate a random int in range [1, 100]
        this.target = rand.nextInt(99) + 1;
    }

    /*
    * returns:
    * 0 if the guess is equal with the target
    * 1 if the guess is greater than the target
    * -1 if the guess is less than the target
    */
    public int checkGuess(Integer guess){
        return Integer.compare(guess, target);

    }
}
