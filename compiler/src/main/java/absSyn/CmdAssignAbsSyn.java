package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdAssignAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final ExpressionAbsSyn exprL;
    private final ExpressionAbsSyn exprR;

    public CmdAssignAbsSyn(ExpressionAbsSyn exprL, ExpressionAbsSyn exprR) {
        this.exprL = exprL;
        this.exprR = exprR;
    }

    @Override
    public void check() throws ContextError {
        //TODO: check if type L und type R are identical
        exprL.check();
        exprR.check();
    }
}
