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
    private final IAbsSyn blockCmd;

    public ProgramAbsSyn(IToken t, List<IAbsSyn> optionalGlobalDeclarationList, List<IAbsSyn> programParameterList, IAbsSyn blockCmd) {
        super(t);
        this.optionalGlobalDeclarationList = optionalGlobalDeclarationList;
        this.programParameterList = programParameterList;
        this.blockCmd = blockCmd;
    }

    /**
     * TODO: move iConcSyn to iAbsSyn object to call check recursively
     */
    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
//        this.optionalGlobalDeclarationList.foreach.check();
//        this.programParameterList.foreach.check();
//        this.blockCmd.check();
    }
}
