package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class IdentsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> RepeatingOptionalIdentsConcSyn;

    public IdentsAbsSyn(IToken t, List<IAbsSyn> RepeatingOptionalIdentsConcSyn) {
        super(t);
        this.RepeatingOptionalIdentsConcSyn = RepeatingOptionalIdentsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
