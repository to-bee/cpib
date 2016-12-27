package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorMoniadicAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private final MonadictOperatorAbsSyn monadictOperatorAbsSyn;
    private final FactorAbsSyn factorAbsSyn;

    public FactorMoniadicAbsSyn(MonadictOperatorAbsSyn monadictOperatorAbsSyn, FactorAbsSyn factorAbsSyn) {

        this.monadictOperatorAbsSyn = monadictOperatorAbsSyn;
        this.factorAbsSyn = factorAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        this.monadictOperatorAbsSyn.check();
        this.factorAbsSyn.check();
    }
}
