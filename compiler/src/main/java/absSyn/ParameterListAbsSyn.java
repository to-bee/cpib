package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ParameterListAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> OptionalParametersConcSyn;

    public ParameterListAbsSyn(IToken t, List<IAbsSyn> OptionalParametersConcSyn) {
        super(t);
        this.OptionalParametersConcSyn = OptionalParametersConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
