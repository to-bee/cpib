package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepMultOprFactorAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final FactorAbsSyn factorAbsSyn;
    private final RepMultOprFactorAbsSyn repMultOprFactorAbsSyn;

    public RepMultOprFactorAbsSyn1(FactorAbsSyn factorAbsSyn, RepMultOprFactorAbsSyn repMultOprFactorAbsSyn) {

        this.factorAbsSyn = factorAbsSyn;
        this.repMultOprFactorAbsSyn = repMultOprFactorAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        factorAbsSyn.check();
        repMultOprFactorAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
