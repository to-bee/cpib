package absSyn;

import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;

import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final List<IConcSyn> optionalGlobalDeclarationList;
    private final List<IConcSyn> programParameterList;
    private final IConcSyn blockCmd;

    public ProgramAbsSyn(IToken t, List<IConcSyn> optionalGlobalDeclarationList, List<IConcSyn> programParameterList, IConcSyn blockCmd) {
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
        this.optionalGlobalDeclarationList.foreach.check();
        this.programParameterList.foreach.check();
        this.blockCmd.check();
    }
}
