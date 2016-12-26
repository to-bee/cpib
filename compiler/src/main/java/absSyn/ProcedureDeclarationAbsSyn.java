package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ProcedureDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> ParameterListConcSyn;
    private final List<IAbsSyn> OptionalGlobalImportsConcSyn;
    private final List<IAbsSyn> OptionalLocalStorageDeclarationsConcSyn;
    private final List<IAbsSyn> BlockCmdConcSyn;

    public ProcedureDeclarationAbsSyn(IToken t, List<IAbsSyn> ParameterListConcSyn, List<IAbsSyn> OptionalGlobalImportsConcSyn, List<IAbsSyn> OptionalLocalStorageDeclarationsConcSyn, List<IAbsSyn> BlockCmdConcSyn) {
        super(t);
        this.ParameterListConcSyn = ParameterListConcSyn;
        this.OptionalGlobalImportsConcSyn = OptionalGlobalImportsConcSyn;
        this.OptionalLocalStorageDeclarationsConcSyn = OptionalLocalStorageDeclarationsConcSyn;
        this.BlockCmdConcSyn = BlockCmdConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
