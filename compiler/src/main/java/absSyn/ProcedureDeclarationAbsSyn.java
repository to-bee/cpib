package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class ProcedureDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn {

    private final ParameterListAbsSyn parameterListAbsSyn;
    private final OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn;
    private final OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn;
    private final BlockCmdAbsSyn blockCmdAbsSyn;

    public ProcedureDeclarationAbsSyn(ParameterListAbsSyn parameterListAbsSyn, OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn, OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn, BlockCmdAbsSyn blockCmdAbsSyn) {

        this.parameterListAbsSyn = parameterListAbsSyn;
        this.optionalGlobalImportsAbsSyn = optionalGlobalImportsAbsSyn;
        this.optionalLocalStorageDeclarationsAbsSyn = optionalLocalStorageDeclarationsAbsSyn;
        this.blockCmdAbsSyn = blockCmdAbsSyn;
    }

    @Override
    public void check() throws ContextError {
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
