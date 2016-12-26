package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class Term3AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> FactorConcSyn;
    private final List<IAbsSyn> RepMultOprFactorConcSyn;

    public Term3AbsSyn(IToken t, List<IAbsSyn> FactorConcSyn, List<IAbsSyn> RepMultOprFactorConcSyn) {
        super(t);
        this.FactorConcSyn = FactorConcSyn;
        this.RepMultOprFactorConcSyn = RepMultOprFactorConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
