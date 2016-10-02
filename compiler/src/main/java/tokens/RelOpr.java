package tokens;

import datatypes.Operators;
import datatypes.Terminals;

/**
 * 
 * @author Roman
 *
 */
public class RelOpr extends BaseToken {
    private final Operators opr;

    RelOpr(Operators t) {
        super(Terminals.RELOPR);
        opr = t;
    }
}