package absSyn;

import scanner.token.IToken;

import java.util.List;

/**
 * Created by ylaub on 20.12.2016.
 */
public class BlockCmdAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final List<IAbsSyn> Cmd;
    private final List<IAbsSyn> RepeatingOptionalCmds;

    public BlockCmdAbsSyn(IToken t, List<IAbsSyn> Cmd, List<IAbsSyn> RepeatingOptionalCmds) {
        super(t);
        this.Cmd = Cmd;
        this.RepeatingOptionalCmds = RepeatingOptionalCmds;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
