package tokens;

import datatypes.Terminals;

/**
 * 
 * @author Roman
 * taken from the slides
 * example: (IDENT, "x36")
 */
public class Ident extends BaseToken {
    private String ident;

    public Ident(String s) {
        super(Terminals.IDENT);
        ident = s;
    }
    
    public String getValue() {
    	return ident;
    }
 
    public String toString() {
    	return ident;
    }
}