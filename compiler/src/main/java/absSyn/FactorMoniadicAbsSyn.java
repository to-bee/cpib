package absSyn;

import context.Variable;
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


    public String toString(int counter) {
        return "FactorMonadicAbsSyn:\r\n\t" + this.exprOpr +"," + exprVariableRight.toString() +"," + monadictOperatorAbsSyn.toString(counter)+"," + factorAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        Variable currentVariable = Variable.getCurrentVariable();
        currentVariable.setExprOpr(this.exprOpr);
        Variable var = Variable.getVar(this.exprVariableRight);
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
