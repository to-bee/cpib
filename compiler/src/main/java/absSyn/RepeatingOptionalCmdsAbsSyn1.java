package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalCmdsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final CmdAbsSyn cmdAbsSyn;
    private final RepeatingOptionalCmdsAbsSyn repeatingOptionalCmdsAbsSyn;

    public RepeatingOptionalCmdsAbsSyn1(CmdAbsSyn cmdAbsSyn, RepeatingOptionalCmdsAbsSyn repeatingOptionalCmdsAbsSyn) {

        this.cmdAbsSyn = cmdAbsSyn;
        this.repeatingOptionalCmdsAbsSyn = repeatingOptionalCmdsAbsSyn;
    }


    public String toString(int counter) {
        return "RepeatingOptionalCmdsAbsSyn1:\r\n\t" + cmdAbsSyn.toString(counter) + "," + repeatingOptionalCmdsAbsSyn.toString(counter);
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
}
