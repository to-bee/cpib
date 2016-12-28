package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class ExpressionAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final Term1AbsSyn term1AbsSyn;
    private final RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn;

    public ExpressionAbsSyn(Term1AbsSyn term1AbsSyn, RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn) {
        this.term1AbsSyn = term1AbsSyn;
        this.repBoolOprTerm1AbsSyn = repBoolOprTerm1AbsSyn;
    }

    @Override
    public void check() throws ContextError {
        term1AbsSyn.check();
        repBoolOprTerm1AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
