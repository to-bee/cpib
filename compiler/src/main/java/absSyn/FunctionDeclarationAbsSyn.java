package absSyn;
import conSyn.IConcSyn;
import conSyn.ParameterListConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class FunctionDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{


    private final Ident ident;
    private final ParameterListAbsSyn parameterListAbsSyn;
    private final StorageDeclarationAbsSyn storageDeclarationAbsSyn;
    private final OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn;
    private final OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn;
    private final BlockCmdAbsSyn blockCmdAbsSyn;

    public FunctionDeclarationAbsSyn(Ident ident, ParameterListAbsSyn parameterListAbsSyn, StorageDeclarationAbsSyn storageDeclarationAbsSyn, OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn, OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn, BlockCmdAbsSyn blockCmdAbsSyn) {

        this.ident = ident;
        this.parameterListAbsSyn = parameterListAbsSyn;
        this.storageDeclarationAbsSyn = storageDeclarationAbsSyn;
        this.optionalGlobalImportsAbsSyn = optionalGlobalImportsAbsSyn;
        this.optionalLocalStorageDeclarationsAbsSyn = optionalLocalStorageDeclarationsAbsSyn;
        this.blockCmdAbsSyn = blockCmdAbsSyn;
    }

    @Override
    public void check() {
        ident.check();
        parameterListAbsSyn.check();
        storageDeclarationAbsSyn.check();
        optionalGlobalImportsAbsSyn.check();
        optionalLocalStorageDeclarationsAbsSyn.check();
        blockCmdAbsSyn.check();
    }
}

