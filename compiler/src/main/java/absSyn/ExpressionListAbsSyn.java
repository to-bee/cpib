package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ExpressionListAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> OptionalExpressionsConcSyn;

    public ExpressionListAbsSyn(IToken t, List<IAbsSyn> OptionalExpressionsConcSyn) {
        super(t);
        this.OptionalExpressionsConcSyn = OptionalExpressionsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
