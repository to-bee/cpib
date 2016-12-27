package absSyn;
/**
 * Created by ylaub on 26.12.2016.
 */
public class RepAddOprTerm3AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Term3AbsSyn term3AbsSyn;
    private final RepAddOprTerm3AbsSyn repAddOprTerm3AbsSyn;

    public RepAddOprTerm3AbsSyn(Term3AbsSyn term3AbsSyn, RepAddOprTerm3AbsSyn repAddOprTerm3AbsSyn) {
        this.term3AbsSyn = term3AbsSyn;
        this.repAddOprTerm3AbsSyn = repAddOprTerm3AbsSyn;
    }

    @Override
    public void check() {
        term3AbsSyn.check();
        repAddOprTerm3AbsSyn.check();
    }
}

