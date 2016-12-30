package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

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

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
