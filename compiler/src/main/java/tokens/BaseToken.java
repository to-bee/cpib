package tokens;

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
        String prefix = terminal.getPrefix();
        String value = terminal.getValue();
        String postfix = terminal.getPostfix();
        StringBuilder sb = new StringBuilder();
        if(prefix != null) {
            sb.append(prefix);
        }
        if(value != null) {
            sb.append(value);
        }
        if(postfix != null) {
            sb.append(postfix);
        }
//        switch(terminal) {
//            case START_ROUTINE:
//                return String.format("%s", terminal.getPrefix());
//            case SENTINEL:
//                return String.format("%s%s", terminal.toString(), terminal.getPostfix());
//            case ENDWHILE:
//                return String.format("%s%s", terminal.toString(), terminal.getPostfix());
//            case DO:
//                return String.format("%s", terminal.toString());
//            case WHILE:
//                return String.format("%s", terminal.toString());
//            default:
//                return String.format("%s%s%s", terminal.getPrefix(), terminal.toString(), terminal.getPostfix());
//        }

        return sb.toString();
    }
}
