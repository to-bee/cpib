package absSyn;

import context.DefaultVar;
import context.Var;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorMoniadicAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private final IToken exprOpr;
    private final IToken exprVariableRight;
    private final MonadictOperatorAbsSyn monadictOperatorAbsSyn;
    private final FactorAbsSyn factorAbsSyn;

    public FactorMoniadicAbsSyn(IToken exprOpr, IToken exprVariableRight, MonadictOperatorAbsSyn monadictOperatorAbsSyn, FactorAbsSyn factorAbsSyn) {
        this.exprOpr = exprOpr;
        this.exprVariableRight = exprVariableRight;
        this.monadictOperatorAbsSyn = monadictOperatorAbsSyn;
        this.factorAbsSyn = factorAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        DefaultVar currentVariable = (DefaultVar) Var.getCurrentVariable();
        currentVariable.setExprOpr(this.exprOpr);
        DefaultVar var = (DefaultVar) Var.getVar(this.exprVariableRight);
        if(var != null) {
            currentVariable.addExprVariable(this.exprVariableRight);
        }

        this.monadictOperatorAbsSyn.check();
        this.factorAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
