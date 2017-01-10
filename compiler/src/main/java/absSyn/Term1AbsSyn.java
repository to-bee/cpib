package absSyn;

import context.Variable;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class Term1AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Term2AbsSyn term2AbsSyn;
    private final RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn;

    public Term1AbsSyn(Term2AbsSyn term2AbsSyn, RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn) {
        this.term2AbsSyn = term2AbsSyn;
        this.repRelOprTerm2AbsSyn = repRelOprTerm2AbsSyn;
    }

    @Override
    public void check() throws ContextError {
        this.term2AbsSyn.check();
        this.repRelOprTerm2AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }

    public Term2AbsSyn getTerm2AbsSyn() {
        return term2AbsSyn;
    }

    public RepRelOprTerm2AbsSyn getRepRelOprTerm2AbsSyn() {
        return repRelOprTerm2AbsSyn;
    }
}

