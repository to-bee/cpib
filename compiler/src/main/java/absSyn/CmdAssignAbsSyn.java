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

        if (Variable.getOpr() != null) {
            if (Variable.getOpr().getTerminal() == Terminal.GT
                    || Variable.getOpr().getTerminal() == Terminal.LT
                    || Variable.getOpr().getTerminal() == Terminal.GE
                    || Variable.getOpr().getTerminal() == Terminal.LE
                    || Variable.getOpr().getTerminal() == Terminal.CAND
                    || Variable.getOpr().getTerminal() == Terminal.COR) {
                if (Variable.getExprVariableLeft().isImaginary()) {
                    throw new ContextError(String.format("%s not allowed for variables of type %s", Variable.getExprVariableLeft(), Terminal.COMPL));
                } else if (Variable.getExprVariableRight().isImaginary()) {
                    throw new ContextError(String.format("%s not allowed for variables of type %s", Variable.getExprVariableRight(), Terminal.COMPL));
                }
            } else if (Variable.getOpr().getTerminal() == Terminal.COMPLEMENT) {
                if (Variable.getExprVariableRight().getType() != Terminal.BOOL) {
                    throw new ContextError(String.format("%s not allowed for variables of type %s", Variable.getExprVariableRight(), Terminal.COMPL));
                }
            }
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
