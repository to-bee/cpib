package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class RepBoolOprTerm1AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Term1AbsSyn term1AbsSyn;
    private final RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn;

    public RepBoolOprTerm1AbsSyn(Term1AbsSyn term1AbsSyn, RepBoolOprTerm1AbsSyn repBoolOprTerm1AbsSyn) {

        this.term1AbsSyn = term1AbsSyn;
        this.repBoolOprTerm1AbsSyn = repBoolOprTerm1AbsSyn;
    }

    @Override
    public void check() {
        this.term1AbsSyn.check();
        this.repBoolOprTerm1AbsSyn.check();
    }
}

