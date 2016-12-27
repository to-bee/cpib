package absSyn;

import absSyn.commands.Cmd;
import scanner.token.IToken;

import java.util.List;

/**
 * Created by ylaub on 20.12.2016.
 */
public class BlockCmdAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final absSyn.commands.Cmd Cmd;
    private final List<IAbsSyn> RepeatingOptionalCmds;

    public BlockCmdAbsSyn(absSyn.commands.Cmd Cmd, List<IAbsSyn> RepeatingOptionalCmds) {
        super(t);
        this.Cmd = Cmd;
        this.RepeatingOptionalCmds = RepeatingOptionalCmds;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
