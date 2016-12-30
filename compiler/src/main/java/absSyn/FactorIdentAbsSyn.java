package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

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

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
