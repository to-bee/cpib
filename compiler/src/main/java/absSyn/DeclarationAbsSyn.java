package absSyn;
import conSyn.FunctionDeclarationConcSyn;
import conSyn.IConcSyn;
import conSyn.ProcedureDeclarationConcSyn;
import conSyn.StorageDeclarationConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class DeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> StorageDeclarationConcSyn;
    private final List<IAbsSyn> FunctionDeclarationConcSyn;
    private final List<IAbsSyn> ProcedureDeclarationConcSyn;

    public DeclarationAbsSyn(IToken t, List<IAbsSyn> StorageDeclarationConcSyn, List<IAbsSyn> FunctionDeclarationConcSyn, List<IAbsSyn> ProcedureDeclarationConcSyn) {
        super(t);
        this.StorageDeclarationConcSyn = StorageDeclarationConcSyn;
        this.FunctionDeclarationConcSyn = FunctionDeclarationConcSyn;
        this.ProcedureDeclarationConcSyn = ProcedureDeclarationConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}

