package tokens;

import datatypes.Terminals;
import interfaces.IToken;

/**
 * Created by tobi on 27/09/16.
 */
public class BaseToken implements IToken{
    private final Terminals terminal;

    public BaseToken(Terminals t) {
        terminal = t;
    }

    public Terminals getTerminal() {
        return terminal;
    }

    @Override
    public String toString() {
        switch(terminal) {
            case WHILE:
                return String.format("%s%s", terminal.getPraeffix(), terminal.toString());
            case DO:
                return String.format("%s", terminal.toString());
            default:
                return String.format("%s%s%s", terminal.getPraeffix(), terminal.toString(), terminal.getSuffix());
        }

    }
}
