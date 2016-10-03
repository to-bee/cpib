package datatypes;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tobi on 27/09/16.
 */
public enum Terminals {
    UNDEFINED,
    IDENT,
    LITERAL,
    RELOPR,
    WHILE,
    DO, 
    SENTINEL;

    Terminals() {

    }

    public static Terminals getTerminalFromString(String value) {
        try {
            return Arrays.asList(Terminals.values()).stream().filter(t -> t.toString().toLowerCase().equals(value.toLowerCase())).findFirst().get();
        }
        catch(Exception e) {
            return Terminals.UNDEFINED;
        }
    }
}
