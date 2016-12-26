package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class StorageDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> OptionalChangeModeConcSyn;
    private final List<IAbsSyn> TypedIdentConcSyn;

    public StorageDeclarationAbsSyn(IToken t, List<IAbsSyn> OptionalChangeModeConcSyn, List<IAbsSyn>  TypedIdentConcSyn) {
        super(t);
        this.OptionalChangeModeConcSyn = OptionalChangeModeConcSyn;
        this.TypedIdentConcSyn = TypedIdentConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}

