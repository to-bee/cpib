package absSyn;

import scanner.token.Ident;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalIdentsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Ident ident;
    private final IdentsAbsSyn identsAbsSyn;

    public RepeatingOptionalIdentsAbsSyn(Ident ident, IdentsAbsSyn identsAbsSyn) {

        this.ident = ident;
        this.identsAbsSyn = identsAbsSyn;
    }

    @Override
    public void check() {
        this.ident.check();
        this.identsAbsSyn.check();
    }
}
