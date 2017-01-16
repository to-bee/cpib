package absSyn;

import context.Var;
import context.DefaultVar;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;
import vm.VmInstructions;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdAssignAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    public static DefaultVar currentVariable;
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
        DefaultVar.setCurrentVariable(this.exprL.getToken());
        DefaultVar currentVariable = (DefaultVar) Var.getCurrentVariable();
        exprR.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return VmInstructions.assign(location, exprL, exprR);
    }

    public String toString(int counter) {
        return "cmdAssignAbsSyn:\r\n\t" +
                exprL.toString(counter) + ":" + exprL.getToken() + "," + exprR.toString(counter);
    }
}
