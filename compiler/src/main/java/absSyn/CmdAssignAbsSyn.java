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

    @Override
    public void check() throws ContextError {
        Variable.setCurrentVariable(this.exprL.getToken());
        Variable currentVariable = Variable.getCurrentVariable();
        exprR.check();

        // TODO: moved
//        if (currentVariable.getOpr() != null) {
//            if (currentVariable.getOpr().getTerminal() == Terminal.GT
//                    || currentVariable.getOpr().getTerminal() == Terminal.LT
//                    || currentVariable.getOpr().getTerminal() == Terminal.GE
//                    || currentVariable.getOpr().getTerminal() == Terminal.LE
//                    || currentVariable.getOpr().getTerminal() == Terminal.CAND
//                    || currentVariable.getOpr().getTerminal() == Terminal.COR) {
//                if (currentVariable.exprVariableContains(Terminal.COMPL)) {
//                    throw new ContextError(String.format("%s not allowed for variables of type %s", currentVariable.getOpr().getTerminal(), Terminal.COMPL));
//                }
//            } else if (currentVariable.getOpr().getTerminal() == Terminal.COMPLEMENT) {
//                if (currentVariable.exprVariableContains(Terminal.BOOL)) {
//                    throw new ContextError(String.format("%s not allowed for variables of type %s", currentVariable.getOpr().getTerminal(), Terminal.COMPL));
//                }
//            }
//        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
