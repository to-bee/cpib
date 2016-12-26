package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalGlobalDeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> DeclarationsConcSyn;

    public OptionalGlobalDeclarationsAbsSyn(IToken t, List<IAbsSyn> DeclarationsConcSyn) {
        super(t);
        this.DeclarationsConcSyn = DeclarationsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
