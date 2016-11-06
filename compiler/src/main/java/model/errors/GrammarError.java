package model.errors;

public class GrammarError extends Exception {
    public GrammarError(String errorMessage) {
        super(errorMessage);
    }
}