package scanner.token;

import scanner.datatypes.Terminal;

/**
 * 
 * @author Roman
 * taken from the slides
 * scanner.token with a literal attribute always have terminal LITERAL
 * example: (LITERAL, 67)
 */
public class Literal extends BaseToken {
    private final double value;

    public Literal(double v) {
        super(Terminal.LITERAL);
        value = v;
    }
    
    double getValue() {
    	return value;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", getTerminal().toString(), getValue());
    }
}



