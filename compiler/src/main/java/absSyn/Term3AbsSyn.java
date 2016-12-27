package absSyn;

/**
 * Created by ylaub on 26.12.2016.
 */
public class Term3AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final FactorAbsSyn factorAbsSyn;
    private final RepMultOprFactorAbsSyn repMultOprFactorAbsSyn;


    public Term3AbsSyn(FactorAbsSyn factorAbsSyn, RepMultOprFactorAbsSyn repMultOprFactorAbsSyn) {
        this.factorAbsSyn = factorAbsSyn;
        this.repMultOprFactorAbsSyn = repMultOprFactorAbsSyn;
    }

    @Override
    public void check() {
        this.factorAbsSyn.check();
        this.repMultOprFactorAbsSyn.check();
    }
}
