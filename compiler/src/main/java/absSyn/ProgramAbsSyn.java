package absSyn;

import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final List<IAbsSyn> optionalGlobalDeclarationList;
    private final List<IAbsSyn> programParameterList;
    private final List<IAbsSyn> BlockCmdConcSyn;

    public ProgramAbsSyn(IToken t, List<IAbsSyn> optionalGlobalDeclarationList, List<IAbsSyn> programParameterList, List<IAbsSyn> BlockCmdConcSyn) {
            super(t);
            this.optionalGlobalDeclarationList = optionalGlobalDeclarationList;
            this.programParameterList = programParameterList;
            this.BlockCmdConcSyn = BlockCmdConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
//        this.optionalGlobalDeclarationList.foreach.check();
//        this.programParameterList.foreach.check();
//        this.blockCmd.check();
    }
}
