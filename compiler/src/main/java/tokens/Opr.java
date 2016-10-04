package tokens;

import datatypes.Operators;
import datatypes.Terminals;

/**
 * @author Roman
 *         example: (RELOPR, LE)
 */
public class Opr extends BaseToken {
    private Operators opr;

    public Opr(Operators o) {
        super(o.getOperatorTypes());
        opr = o;
    }

    @Override
    public String toString() {
        if(getTerminal() == Terminals.UNDEFINED) {
            return String.format("%s, ", getOpr());
        } else {
            return String.format("%s%s, %s%s", getTerminal().getPrefix(), getTerminal().toString(), getOpr(), getTerminal().getPostfix());
        }
    }

    public Operators getOpr() {
        return opr;
    }
}