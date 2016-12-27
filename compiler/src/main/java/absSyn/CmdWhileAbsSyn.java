package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdWhileAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final ExpressionAbsSyn exprWhile;
    private final BlockCmdAbsSyn cmdWhile;

    public CmdWhileAbsSyn(ExpressionAbsSyn exprWhile, BlockCmdAbsSyn cmdWhile) {

        this.exprWhile = exprWhile;
        this.cmdWhile = cmdWhile;
    }

    @Override
    public void check() throws ContextError {
        exprWhile.check();
        cmdWhile.check();
    }
}
