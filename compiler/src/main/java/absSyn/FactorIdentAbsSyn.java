package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorIdentAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private final Ident ident;
    private final OptionalIdentAbsSyn optionalIdentAbsSyn;

    public FactorIdentAbsSyn(Ident ident, OptionalIdentAbsSyn optionalIdentAbsSyn) {

        this.ident = ident;
        this.optionalIdentAbsSyn = optionalIdentAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
        optionalIdentAbsSyn.check();
    }
}
