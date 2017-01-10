package absSyn;

import context.Variable;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdAssignAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    public static Variable currentVariable;
    private final ExpressionAbsSyn exprL;
    private final ExpressionAbsSyn exprR;

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
        if (var == null) {
            throw new ContextError(String.format("A variable: \"%s\" doesn't exist in this context", this.exprL.getToken()));
        } else {
            currentVariable = var;
        }

        Variable.resetExpr();
        exprR.check();

        if (Variable.getRelOpr() != null &&
                (Variable.getRelOpr().getTerminal() == Terminal.GT
                        || Variable.getRelOpr().getTerminal() == Terminal.LT
                        || Variable.getRelOpr().getTerminal() == Terminal.GE
                        || Variable.getRelOpr().getTerminal() == Terminal.LE)) {
            if (Variable.getRelOprVariableLeft().isImaginary()) {
                throw new ContextError(String.format("%s not allowed for variables of type %s", Variable.getRelOprVariableLeft(), Terminal.COMPL));
            } else if (Variable.getRelOprVariableRight().isImaginary()) {
                throw new ContextError(String.format("%s not allowed for variables of type %s", Variable.getRelOprVariableRight(), Terminal.COMPL));
            }
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
