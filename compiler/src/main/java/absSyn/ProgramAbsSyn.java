package absSyn;

import conSyn.BlockCmdConcSyn;
import conSyn.OptionalGlobalDeclarationsConcSyn;
import conSyn.ProgramParameterListConcSyn;
import scanner.errors.ContextError;
import scanner.token.Ident;

/**
 * Created by tobi on 17.12.16.
 */
public class ProgramAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    /**
     * Programs identifier
     */
    private Ident ident;
    private final OptionalGlobalDeclarationsAbsSyn optionalGlobalDeclarationList;
    private final ProgramParameterListAbsSyn programParameterList;
    private final BlockCmdAbsSyn blockCmdConcSyn;

    public ProgramAbsSyn(Ident ident, ProgramParameterListAbsSyn programParameterListConcSyn, OptionalGlobalDeclarationsAbsSyn optionalGlobalDeclarationsConcSyn, BlockCmdAbsSyn blockCmdConcSyn) {
        this.ident = ident;
        this.optionalGlobalDeclarationList = optionalGlobalDeclarationsConcSyn;
        this.programParameterList = programParameterListConcSyn;
        this.blockCmdConcSyn = blockCmdConcSyn;
    }

    @Override
    public void check() throws ContextError {
        if(this.ident.getValue().length() > 256) {
            throw new ContextError("Ident too long");
        }
        optionalGlobalDeclarationList.check();
        programParameterList.check();
        blockCmdConcSyn.check();
    }
}
