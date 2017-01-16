package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;
import vm.VmInstructions;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdIfAbsSyn extends AbstractAbsSyn implements IAbsSyn {

    private final ExpressionAbsSyn exprIf;
    private final BlockCmdAbsSyn cmdThen;
    private final BlockCmdAbsSyn cmdElse;
    private final Terminal terminal;

    public CmdIfAbsSyn(ExpressionAbsSyn exprIf, BlockCmdAbsSyn cmdThen, BlockCmdAbsSyn cmdElse, Terminal terminal) {
        this.exprIf = exprIf;
        this.cmdThen = cmdThen;
        this.cmdElse = cmdElse;
        this.terminal = terminal;
    }

    @Override
    public void check() throws ContextError {

        exprIf.check();
        cmdThen.check();
        cmdElse.check();
    }


    public String toString(int counter) {
        return "cmdIfAbsSyn:\r\n\t" + exprIf.toString(counter) + "," + cmdThen.toString(counter)+ "," + cmdElse.toString(counter);
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location; // TODO
    }
}
