package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class Term2AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> Term3ConcSyn;
    private final List<IAbsSyn> RepAddOprTerm3ConcSyn;

    public Term2AbsSyn(IToken t, List<IAbsSyn> Term3ConcSyn, List<IAbsSyn> RepAddOprTerm3ConcSyn) {
        super(t);
        this.Term3ConcSyn = Term3ConcSyn;
        this.RepAddOprTerm3ConcSyn = RepAddOprTerm3ConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}

