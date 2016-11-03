package model.token;

import model.datatypes.Terminals;

/**
 * 
 * @author Roman
 * taken from the slides
 * model.token with a literal attribute always have terminal LITERAL
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

    @Override
    public String toString() {
        return String.format("%s%s, %s%s", getTerminal().getPrefix(), getTerminal().toString(), getValue(), getTerminal().getPostfix());
    }
}



