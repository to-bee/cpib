package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalIdentsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {

    private final Ident ident;
    private final IdentsAbsSyn identsAbsSyn;

    public RepeatingOptionalIdentsAbsSyn1(Ident ident, IdentsAbsSyn identsAbsSyn) {

        this.ident = ident;
        this.identsAbsSyn = identsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
        identsAbsSyn.check();
    }
}
