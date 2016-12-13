package scanner.token;

import scanner.datatypes.TerminalType;
import scanner.datatypes.Terminal;

/**
 * @author Roman
 *         example: (RELOPR, LE)
 */
public class Opr extends BaseToken {
    public Opr(Terminal t) {
        super(t);
    }

    @Override
    public String toString() {
        if(getTerminal() == Terminal.UNDEFINED) {
            return String.format("%s, ", getTerminal());
        } else {
            return String.format("%s, %s", getTerminal().toString(), getTerminal());
        }
    }
}