package scanner.states;

import scanner.Scanner;
import scanner.errors.LexicalError;
import scanner.token.*;

/**
 * Created by tobi on 12.12.16.
 */
public abstract class AbstractTokenState implements ITokenState {
    private final Scanner owner;

    public AbstractTokenState(Scanner owner) {
        this.owner = owner;
    }

    public Scanner getOwner() {
        return owner;
    }

    protected static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    protected abstract IToken getTokenByWord(String word);

    protected void addTokenFromWord() {
        String word = this.word.toString();
        if(word.length() > 0) {
            IToken token = getTokenByWord(word);
            if(token != null) {
                getOwner().getTokenList().add(token);
            }
            // Reset - otherwise token will be added twice
            this.word = new StringBuilder();
        }
    }

    protected boolean isInitial() {
        return word.length() == 0;
    }

    private StringBuilder word = new StringBuilder();

    public StringBuilder getWord() {
        return word;
    }
}
