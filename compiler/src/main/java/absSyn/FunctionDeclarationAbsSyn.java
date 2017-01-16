package absSyn;
import conSyn.IConcSyn;
import conSyn.ParameterListConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

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
    private final Terminal terminal;

    public FunctionDeclarationAbsSyn(Ident ident, ParameterListAbsSyn parameterListAbsSyn, StorageDeclarationAbsSyn storageDeclarationAbsSyn, OptionalGlobalImportsAbsSyn optionalGlobalImportsAbsSyn, OptionalLocalStorageDeclarationsAbsSyn optionalLocalStorageDeclarationsAbsSyn, BlockCmdAbsSyn blockCmdAbsSyn, Terminal terminal) {

        this.ident = ident;
        this.parameterListAbsSyn = parameterListAbsSyn;
        this.storageDeclarationAbsSyn = storageDeclarationAbsSyn;
        this.optionalGlobalImportsAbsSyn = optionalGlobalImportsAbsSyn;
        this.optionalLocalStorageDeclarationsAbsSyn = optionalLocalStorageDeclarationsAbsSyn;
        this.blockCmdAbsSyn = blockCmdAbsSyn;
        this.terminal = terminal;
    }

    public String toString(int counter) {
        return "FunctionDeclarationAbsSyn:\r\n\t" + parameterListAbsSyn.toString(counter) +"," + storageDeclarationAbsSyn.toString(counter) +"," + optionalGlobalImportsAbsSyn.toString(counter)+"," + optionalLocalStorageDeclarationsAbsSyn.toString(counter) + "," + blockCmdAbsSyn.toString(counter) + "," + this.terminal;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
        parameterListAbsSyn.check();
        storageDeclarationAbsSyn.check();
        optionalGlobalImportsAbsSyn.check();
        optionalLocalStorageDeclarationsAbsSyn.check();
        blockCmdAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}

