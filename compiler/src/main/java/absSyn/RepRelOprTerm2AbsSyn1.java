package absSyn;

import context.Variable;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepRelOprTerm2AbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private IToken relOpr;
    private IToken relOprVariableRight;
    private final Term2AbsSyn term2AbsSyn;
    private final RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn;

    public RepRelOprTerm2AbsSyn1(IToken relOpr, IToken relOprVariableRight, Term2AbsSyn term2AbsSyn, RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn) {
        this.relOpr = relOpr;
        this.relOprVariableRight = relOprVariableRight;
        this.term2AbsSyn = term2AbsSyn;
        this.repRelOprTerm2AbsSyn = repRelOprTerm2AbsSyn;
    }

    @Override
    public void check() throws ContextError {
        Variable.setRelOpr(this.relOpr);
        Variable.setRelOprVariableRight(this.relOprVariableRight);

        term2AbsSyn.check();
        repRelOprTerm2AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
