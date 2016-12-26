package absSyn;

import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> ExpressionConcSyn;
    private final List<IAbsSyn> BlockCmdConcSyn;
    private final List<IAbsSyn> ExpressionListConcSyn;
    private final List<IAbsSyn> OptionalGlobalInitsConcSyn;

    public CmdAbsSyn(IToken t, List<IAbsSyn> ExpressionConcSyn, List<IAbsSyn> BlockCmdConcSyn, List<IAbsSyn> ExpressionListConcSyn, List<IAbsSyn> OptionalGlobalInitsConcSyn) {
        super(t);
        this.ExpressionConcSyn = ExpressionConcSyn;
        this.BlockCmdConcSyn = BlockCmdConcSyn;
        this.ExpressionListConcSyn = ExpressionListConcSyn;
        this.OptionalGlobalInitsConcSyn = OptionalGlobalInitsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
