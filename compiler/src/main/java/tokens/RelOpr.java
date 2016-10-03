package tokens;

import datatypes.Operators;
import datatypes.Terminals;

/**
 * 
 * @author Roman
 * example: (RELOPR, LE)
 *
 */
public class RelOpr extends BaseToken {
    private final Operators opr;

    public RelOpr(Operators t) {
        super(Terminals.RELOPR);
        opr = t;
    }
}