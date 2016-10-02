package tokens;

import datatypes.Terminals;

/**
 * 
 * @author Roman
 * taken from the slides
 */
public class Literal extends BaseToken {
    private final int value;

    Literal(int v) {
        super(Terminals.LITERAL);
        value = v;
    }
    
    int getValue() {
    	return value;
    }
    
    public String toSting() {
    	return "(" + super.toString() + "," + value + ")";
    }
}



