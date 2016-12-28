package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdIfAbsSyn extends AbstractAbsSyn implements IAbsSyn {

    private final ExpressionAbsSyn exprIf;
    private final BlockCmdAbsSyn cmdThen;
    private final BlockCmdAbsSyn cmdElse;

    public CmdIfAbsSyn(ExpressionAbsSyn exprIf, BlockCmdAbsSyn cmdThen, BlockCmdAbsSyn cmdElse) {
        this.exprIf = exprIf;
        this.cmdThen = cmdThen;
        this.cmdElse = cmdElse;
    }

    @Override
    public void check() throws ContextError {

        exprIf.check();
        cmdThen.check();
        cmdElse.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
