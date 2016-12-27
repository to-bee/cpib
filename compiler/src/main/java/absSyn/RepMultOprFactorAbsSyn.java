package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepMultOprFactorAbsSyn extends AbstractAbsSyn implements IAbsSyn {

    private final FactorAbsSyn factorAbsSyn;
    private final RepMultOprFactorAbsSyn repMultOprFactorAbsSyn;

    public RepMultOprFactorAbsSyn(FactorAbsSyn factorAbsSyn, RepMultOprFactorAbsSyn repMultOprFactorAbsSyn) {

        this.factorAbsSyn = factorAbsSyn;
        this.repMultOprFactorAbsSyn = repMultOprFactorAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        this.factorAbsSyn.check();
        this.repMultOprFactorAbsSyn.check();
    }
}
