package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalGlobalInitsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> IdentsConcSyn;

    public OptionalGlobalInitsAbsSyn(IToken t, List<IAbsSyn> IdentsConcSyn) {
        super(t);
        this.IdentsConcSyn = IdentsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
