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


    private IAbsSyn subType;

    public DeclarationAbsSyn(IAbsSyn subType) {

        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }
}

