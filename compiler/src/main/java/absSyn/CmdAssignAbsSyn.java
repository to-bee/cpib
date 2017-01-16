package absSyn;

import context.Context;
import context.DefaultVar;
import context.VmVar;
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
        // getLparen() x
        this.exprL = exprL;
        // getLparen() 5
        this.exprR = exprR;
    }

    @Override
    public void check() throws ContextError {
        DefaultVar.setCurrentVariable(this.exprL.getToken());

        VmVar vmVar = Context.setCurrentVmVariable(this.exprL.getToken());
        vmVar.addAssignment();

        exprL.check();
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
