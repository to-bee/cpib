package literals;

import enums.Terminals;

/**
 * Created by tobi on 27/09/16.
 */
public abstract class BaseLexical {
    private final Terminals terminal;

    BaseLexical(Terminals t) {
        terminal = t;
    }

    Terminals getTerminal() {
        return terminal;
    }

    public String toString() {
        return terminal.toString();
    }
}
