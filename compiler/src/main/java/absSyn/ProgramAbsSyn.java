package absSyn;

import conSyn.BlockCmdConcSyn;
import conSyn.OptionalGlobalDeclarationsConcSyn;
import conSyn.ProgramParameterListConcSyn;
import scanner.errors.ContextError;
import scanner.token.Ident;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import virtualmachineFS2015.ICodeArray;

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

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }

    @Override
    public String toString() {
        return this.toString(0);
    }

    public String toString(int counter) {
        StringBuilder sb = new StringBuilder();
        String tabs = getTabs(counter);
        sb.append(String.format("%s%s: %s\n", tabs, getClass().getName(), this.ident.toString()));
        counter++;
        sb.append(optionalGlobalDeclarationList.toString(counter));
        sb.append(programParameterList.toString(counter));
        sb.append(blockCmdConcSyn.toString(counter));
        return sb.toString();
    }
}
