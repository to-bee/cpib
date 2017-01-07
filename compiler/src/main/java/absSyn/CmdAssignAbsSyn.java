package absSyn;

import context.Variable;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdAssignAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final ExpressionAbsSyn exprL;
    private final ExpressionAbsSyn exprR;

    public static Variable currentVariable;

    public CmdAssignAbsSyn(ExpressionAbsSyn exprL, ExpressionAbsSyn exprR) {
        // getToken() x
        this.exprL = exprL;
        // getToken() 5
        this.exprR = exprR;
    }

    public static Variable getCurrentVariable() {
        return currentVariable;
    }

    @Override
    public void check() throws ContextError {
        Variable var = Variable.getVar(this.exprL.getToken());
        if(var == null) {
            throw new ContextError("A variable with this name doesn't exist in this context");
        } else {
            currentVariable = var;
        }

        exprR.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
