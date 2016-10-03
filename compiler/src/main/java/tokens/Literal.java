package tokens;

import datatypes.Terminals;

/**
 * 
 * @author Roman
 * taken from the slides
 * tokens with a literal attribute always have terminal LITERAL
 * example: (LITERAL, 67)
 */
public class Literal extends BaseToken {
    private final double value;

    public Literal(double v) {
        super(Terminals.LITERAL);
        value = v;
    }
    
    double getValue() {
    	return value;
    }
    
    public String toSting() {
        return String.format("(%s,%s)", super.toString(), value);
    }
}



