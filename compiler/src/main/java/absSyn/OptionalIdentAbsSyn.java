package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalIdentAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> ExpressionListConcSyn;

    public OptionalIdentAbsSyn(IToken t, List<IAbsSyn> ExpressionListConcSyn) {
        super(t);
        this.ExpressionListConcSyn = ExpressionListConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
