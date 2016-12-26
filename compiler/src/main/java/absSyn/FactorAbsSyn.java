package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> OptionalIdentConcSyn;
    private final List<IAbsSyn> MonadictOperatorConcSyn;
    private final List<IAbsSyn> FactorConcSyn;
    private final List<IAbsSyn> ExpressionConcSyn;
    private final List<IAbsSyn> RepeatingOptionalExpressionsConcSyn;
    private final List<IAbsSyn> ComplImagConcSyn;
    private final List<IAbsSyn> ComplRealConcSyn;


    public FactorAbsSyn(IToken t, List<IAbsSyn> OptionalIdentConcSyn, List<IAbsSyn> MonadictOperatorConcSyn, List<IAbsSyn> FactorConcSyn, List<IAbsSyn> ExpressionConcSyn, List<IAbsSyn> RepeatingOptionalExpressionsConcSyn, List<IAbsSyn> ComplImagConcSyn, List<IAbsSyn> ComplRealConcSyn) {
        super(t);
        this.OptionalIdentConcSyn = OptionalIdentConcSyn;
        this.MonadictOperatorConcSyn = MonadictOperatorConcSyn;
        this.FactorConcSyn = FactorConcSyn;
        this.ExpressionConcSyn = ExpressionConcSyn;
        this.RepeatingOptionalExpressionsConcSyn = RepeatingOptionalExpressionsConcSyn;
        this.ComplImagConcSyn = ComplImagConcSyn;
        this.ComplRealConcSyn = ComplRealConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
