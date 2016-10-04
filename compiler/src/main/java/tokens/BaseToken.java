package tokens;

import datatypes.Operators;
import datatypes.Terminals;
import interfaces.IToken;

import java.util.List;

/**
 * Created by tobi on 27/09/16.
 */
public class BaseToken implements IToken{
    private final Terminals terminal;

    public BaseToken(Terminals t) {
        terminal = t;
    }

    public BaseToken(List<Terminals> operatorTypes) {
        if(operatorTypes.size() == 1) {
            this.terminal = operatorTypes.get(0);
        } else {
            this.terminal = Terminals.UNDEFINED;
        }
    }

    public Terminals getTerminal() {
        return terminal;
    }

    @Override
    public String toString() {
        switch(terminal) {
            case WHILE:
                return String.format("%s%s", terminal.getPraeffix(), terminal.toString());
            case ENDWHILE:
                return String.format("%s%s", terminal.toString(), terminal.getSuffix());
            case DO:
                return String.format("%s", terminal.toString());
            default:
                return String.format("%s%s%s", terminal.getPraeffix(), terminal.toString(), terminal.getSuffix());
        }

    }
}
