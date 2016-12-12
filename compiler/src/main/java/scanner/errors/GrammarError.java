package scanner.errors;

public class GrammarError extends Exception {
    public GrammarError(String errorMessage) {
        super(errorMessage);
    }
}