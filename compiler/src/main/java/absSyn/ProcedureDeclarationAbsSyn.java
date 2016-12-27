package absSyn;
import scanner.errors.ContextError;
import scanner.token.Ident;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ProcedureDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Ident ident;
    private final ParameterListAbsSyn parameterListAbsSyn;
    private final OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn;
    private final OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn;
    private final BlockCmdAbsSyn blockCmdAbsSyn;

    public ProcedureDeclarationAbsSyn(Ident ident, ParameterListAbsSyn parameterListAbsSyn, OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn, OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn, BlockCmdAbsSyn blockCmdAbsSyn) {

        this.ident = ident;
        this.parameterListAbsSyn = parameterListAbsSyn;
        this.optionalGlobalImportsAbsSyn = optionalGlobalImportsAbsSyn;
        this.optionalLocalStorageDeclarationsAbsSyn = optionalLocalStorageDeclarationsAbsSyn;
        this.blockCmdAbsSyn = blockCmdAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
        parameterListAbsSyn.check();
        optionalGlobalImportsAbsSyn.check();
        optionalLocalStorageDeclarationsAbsSyn.check();
        blockCmdAbsSyn.check();
    }
}
