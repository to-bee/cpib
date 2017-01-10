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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ident ident1 = (Ident) o;

        return ident.equals(ident1.ident);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ident.hashCode();
        return result;
    }

    public Ident(String s) {
        super(Terminal.IDENT);

        ident = s;
    }
    
    public String getValue() {
    	return ident;
    }

    public void check() throws ContextError {
//        throw new ContextError();
    }

    @Override
    public String toString() {
        return String.format("%s, \"%s\"", getTerminal().toString(), getValue());
    }
}