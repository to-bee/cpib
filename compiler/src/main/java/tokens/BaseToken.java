package tokens;

import datatypes.Terminals;
import interfaces.IToken;

/**
 * Created by tobi on 27/09/16.
 */
public class BaseToken implements IToken{
    private final Terminals terminal;

    BaseToken(Terminals t) {
        terminal = t;
    }

    public Terminals getTerminal() {
        return terminal;
    }

    public String toString() {
        return terminal.toString();
    }
}
