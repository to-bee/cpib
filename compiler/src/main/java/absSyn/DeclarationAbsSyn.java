package absSyn;
import conSyn.FunctionDeclarationConcSyn;
import conSyn.IConcSyn;
import conSyn.ProcedureDeclarationConcSyn;
import conSyn.StorageDeclarationConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class DeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final FunctionDeclarationAbsSyn functionDeclarationAbsSyn;
    private final StorageDeclarationAbsSyn storageDeclarationAbsSyn;
    private final ProcedureDeclarationAbsSyn procedureDeclarationAbsSyn;

    public DeclarationAbsSyn(StorageDeclarationAbsSyn storageDeclarationAbsSyn, FunctionDeclarationAbsSyn functionDeclarationAbsSyn, ProcedureDeclarationAbsSyn procedureDeclarationAbsSyn) {
        this.storageDeclarationAbsSyn = storageDeclarationAbsSyn;
        this.functionDeclarationAbsSyn = functionDeclarationAbsSyn;
        this.procedureDeclarationAbsSyn = procedureDeclarationAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        storageDeclarationAbsSyn.check();
        functionDeclarationAbsSyn.check();
        procedureDeclarationAbsSyn.check();
    }
}

