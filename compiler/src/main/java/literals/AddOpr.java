package literals;

import enums.Terminals;
import sun.tools.jstat.Operator;

public class AddOpr extends BaseLexical {
    private Operator opr;

    AddOpr(Terminals t) {
        super(t);
    }
}