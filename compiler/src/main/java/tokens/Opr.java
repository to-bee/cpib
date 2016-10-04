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
        super(o.getOperatorType());
        opr = o;
    }

    @Override
    public String toString() {
        return String.format("%s%s, %s%s", getTerminal().getPraeffix(), getTerminal().toString(), getOpr(), getTerminal().getSuffix());
    }

    public Operators getOpr() {
        return opr;
    }
}