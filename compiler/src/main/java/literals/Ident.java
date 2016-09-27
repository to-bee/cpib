package literals;

import enums.Terminals;

public class Ident extends BaseLexical {
    private String ident;

    Ident(Terminals t) {
        super(t);
    }
}