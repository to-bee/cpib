package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 20.12.2016.
 */
public class BlockCmdAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final CmdAbsSyn cmdAbsSyn;
    private final RepeatingOptionalCmdsAbsSyn repeatingOptionalCmdsAbsSyn;

    public BlockCmdAbsSyn(CmdAbsSyn cmdAbsSyn, RepeatingOptionalCmdsAbsSyn repeatingOptionalCmdsAbsSyn) {
        this.cmdAbsSyn = cmdAbsSyn;
        this.repeatingOptionalCmdsAbsSyn = repeatingOptionalCmdsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        cmdAbsSyn.check();
        repeatingOptionalCmdsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }

    public String toString(int counter) {
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(counter);
        sb.append(String.format("%s%s\n", tabs, getClass().getName()));
        counter++;
        sb.append(cmdAbsSyn.toString(counter));
        sb.append(repeatingOptionalCmdsAbsSyn.toString(counter));
        return sb.toString();
    }
}
