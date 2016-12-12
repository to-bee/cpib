package scanner.token;

import scanner.datatypes.TerminalType;
import scanner.datatypes.Terminal;

/**
 * @author Roman
 *         example: (RELOPR, LE)
 */
public class Opr extends BaseToken {
    private TerminalType opr;

    public Opr(TerminalType o) {
        super(o.getOperatorTypes());
        opr = o;
    }

    @Override
    public String toString() {
        if(getTerminal() == Terminal.UNDEFINED) {
            return String.format("%s, ", getOpr());
        } else {
            return String.format("%s, %s", getTerminal().toString(), getOpr());
        }
    }

    public TerminalType getOpr() {
        return opr;
    }
}