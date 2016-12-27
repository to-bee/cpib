package scanner.token;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;

/**
 * 
 * @author Roman
 * taken from the slides
 * example: (IDENT, "x36")
 */
public class Ident extends BaseToken {
    private String ident;

    public Ident(String s) {
        super(Terminal.IDENT);
        ident = s;
    }
    
    public String getValue() {
    	return ident;
    }

    public void check() {
//        throw new ContextError();
    }

    @Override
    public String toString() {
        return String.format("%s, \"%s\"", getTerminal().toString(), getValue());
    }
}