package absSyn;

import context.Variable;
import context.DefaultVariable;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepBoolOprTerm1AbsSyn1 extends AbstractAbsSyn implements IAbsSyn{
    private final IToken exprOpr;
    private final IToken exprVarRight;
    private final Term1AbsSyn term1AbsSyn;
    private final RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn;

    public RepBoolOprTerm1AbsSyn1(IToken exprOpr, IToken exprVarRight, Term1AbsSyn term1AbsSyn, RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn) {
        this.exprOpr = exprOpr;
        this.exprVarRight = exprVarRight;
        this.term1AbsSyn = term1AbsSyn;
        this.repBoolOprTerm1AbsSyn = repBoolOprTerm1AbsSyn;
    }

    @Override
    public void check() throws ContextError {
        DefaultVariable currentVariable = (DefaultVariable) Variable.getCurrentVariable();
        currentVariable.setExprOpr(this.exprOpr);
        DefaultVariable var = (DefaultVariable) Variable.getVar(this.exprVarRight);
        if(var != null) {
            currentVariable.addExprVariable(this.exprVarRight);
        }

        term1AbsSyn.check();
        repBoolOprTerm1AbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
