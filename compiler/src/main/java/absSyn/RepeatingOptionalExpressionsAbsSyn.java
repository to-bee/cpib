package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalExpressionsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> ExpressionConcSyn;
    private final List<IAbsSyn> RepeatingOptionalExpressionsConcSyn;

    public RepeatingOptionalExpressionsAbsSyn(IToken t, List<IAbsSyn> ExpressionConcSyn, List<IAbsSyn> RepeatingOptionalExpressionsConcSyn) {
        super(t);
        this.ExpressionConcSyn = ExpressionConcSyn;
        this.RepeatingOptionalExpressionsConcSyn = RepeatingOptionalExpressionsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}

