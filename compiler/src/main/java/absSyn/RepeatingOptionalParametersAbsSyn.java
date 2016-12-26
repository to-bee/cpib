package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalParametersAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> ParameterConcSyn;
    private final List<IAbsSyn> RepeatingOptionalParametersConcSyn;

    public RepeatingOptionalParametersAbsSyn(IToken t, List<IAbsSyn> ParameterConcSyn, List<IAbsSyn>  RepeatingOptionalParametersConcSyn) {
        super(t);
        this.ParameterConcSyn = ParameterConcSyn;
        this.RepeatingOptionalParametersConcSyn = RepeatingOptionalParametersConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
