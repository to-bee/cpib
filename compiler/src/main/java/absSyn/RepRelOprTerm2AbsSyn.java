package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class RepRelOprTerm2AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> Term2ConcSyn;
    private final List<IAbsSyn> RepRelOprTerm2ConcSyn;

    public RepRelOprTerm2AbsSyn(IToken t, List<IAbsSyn> Term2ConcSyn, List<IAbsSyn>  RepRelOprTerm2ConcSyn) {
        super(t);
        this.Term2ConcSyn = Term2ConcSyn;
        this.RepRelOprTerm2ConcSyn = RepRelOprTerm2ConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}

