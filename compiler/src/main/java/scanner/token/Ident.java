package scanner.token;

import scanner.datatypes.Terminal;

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

    public String getIdent() {
        return ident;
    }

    @Override
    public String toString() {
        return String.format("(%s, \"%s\")", getTerminal().toString(), getIdent());
    }
}