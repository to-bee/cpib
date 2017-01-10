package scanner.token;

import context.Type;
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

    public Terminal getTerminal() {
        return terminal;
    }

    @Override
    public String toString() {
        return String.format("%s", getTerminal().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseToken baseToken = (BaseToken) o;

        return terminal == baseToken.terminal;
    }

    @Override
    public int hashCode() {
        return terminal.hashCode();
    }
}
