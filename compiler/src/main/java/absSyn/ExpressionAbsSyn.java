package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ExpressionAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> Term1ConcSyn;
    private final List<IAbsSyn> RepBoolOprTerm1ConcSyn;

    public ExpressionAbsSyn(IToken t, List<IAbsSyn> Term1ConcSyn, List<IAbsSyn> RepBoolOprTerm1ConcSyn) {
        super(t);
        this.Term1ConcSyn = Term1ConcSyn;
        this.RepBoolOprTerm1ConcSyn = RepBoolOprTerm1ConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
