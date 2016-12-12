package scanner.token;

import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;

import java.util.List;

/**
 * Created by tobi on 27/09/16.
 */
public class BaseToken implements IToken{
    private final Terminal terminal;

    public BaseToken(Terminal t) {
        terminal = t;
    }

    public BaseToken(List<Terminal> operatorTypes) {
        if(operatorTypes.size() == 1) {
            this.terminal = operatorTypes.get(0);
        } else {
            this.terminal = Terminal.UNDEFINED;
        }
    }

    public Terminal getTerminal() {
        return terminal;
    }

    @Override
    public String toString() {
        return String.format("%s", getTerminal().toString());
    }
}
