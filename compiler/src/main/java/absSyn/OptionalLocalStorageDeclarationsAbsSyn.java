package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalLocalStorageDeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> StorageDeclarationConcSyn;
    private final List<IAbsSyn> RepeatingOptionalStorageDeclarationsConcSyn;

    public OptionalLocalStorageDeclarationsAbsSyn(IToken t, List<IAbsSyn> StorageDeclarationConcSyn, List<IAbsSyn> RepeatingOptionalStorageDeclarationsConcSyn) {
        super(t);
        this.StorageDeclarationConcSyn = StorageDeclarationConcSyn;
        this.RepeatingOptionalStorageDeclarationsConcSyn = RepeatingOptionalStorageDeclarationsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
