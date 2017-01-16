package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepAddOprTerm3AbsSyn1 extends AbstractAbsSyn implements IAbsSyn {

    private final Term3AbsSyn term3AbsSyn;
    private final RepAddOprTerm3AbsSyn repAddOprTerm3AbsSyn;

    public RepAddOprTerm3AbsSyn1(Term3AbsSyn term3AbsSyn, RepAddOprTerm3AbsSyn repAddOprTerm3AbsSyn) {

        this.term3AbsSyn = term3AbsSyn;
        this.repAddOprTerm3AbsSyn = repAddOprTerm3AbsSyn;
    }

    public String toString(int counter) {
        return "RepAddOprTerm3AbsSyn1:\r\n\t" + term3AbsSyn.toString(counter) + "," + repAddOprTerm3AbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        term3AbsSyn.check();
        repAddOprTerm3AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
