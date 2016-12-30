package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepRelOprTerm2AbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final Term2AbsSyn term2AbsSyn;
    private final RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn;

    public RepRelOprTerm2AbsSyn1(Term2AbsSyn term2AbsSyn, RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn) {

        this.term2AbsSyn = term2AbsSyn;
        this.repRelOprTerm2AbsSyn = repRelOprTerm2AbsSyn;
    }

    @Override
    public void check() throws ContextError {
        term2AbsSyn.check();
        repRelOprTerm2AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
