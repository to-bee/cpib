package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;
import scanner.token.Ident;

/**
 * Created by ylaub on 26.12.2016.
 */
public class ProcedureDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn {

    private Ident ident;
    private final ParameterListAbsSyn parameterListAbsSyn;
    private final OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn;
    private final OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn;
    private final BlockCmdAbsSyn blockCmdAbsSyn;
    private final Terminal terminal;

    public ProcedureDeclarationAbsSyn(Ident ident, ParameterListAbsSyn parameterListAbsSyn, OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn, OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn, BlockCmdAbsSyn blockCmdAbsSyn, Terminal terminal) {
        this.ident = ident;

        this.parameterListAbsSyn = parameterListAbsSyn;
        this.optionalGlobalImportsAbsSyn = optionalGlobalImportsAbsSyn;
        this.optionalLocalStorageDeclarationsAbsSyn = optionalLocalStorageDeclarationsAbsSyn;
        this.blockCmdAbsSyn = blockCmdAbsSyn;
        this.terminal = terminal;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
        this.parameterListAbsSyn.check();
        this.optionalGlobalImportsAbsSyn.check();
        this.optionalLocalStorageDeclarationsAbsSyn.check();
        this.blockCmdAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
