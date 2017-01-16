package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdWhileAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final ExpressionAbsSyn exprWhile;
    private final BlockCmdAbsSyn cmdWhile;
    private final Terminal terminal;

    public CmdWhileAbsSyn(ExpressionAbsSyn exprWhile, BlockCmdAbsSyn cmdWhile, Terminal terminal) {
        this.exprWhile = exprWhile;
        this.cmdWhile = cmdWhile;
        this.terminal = terminal;
    }



    public String toString(int counter) {
        return "cmdWhileAbsSyn:\r\n\t" + exprWhile.toString(counter) + "," + cmdWhile.toString(counter)+ "," + this.terminal;
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
