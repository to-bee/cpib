package absSyn;

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
    public void check() {
        cmdAbsSyn.check();
        repeatingOptionalCmdsAbsSyn.check();
    }
}
