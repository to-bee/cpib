package tokens;

import datatypes.Terminals;

/**
 * 
 * @author Roman
 * taken from the slides
 */
public class Ident extends BaseToken {
    private String ident;

    Ident(String s) {
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