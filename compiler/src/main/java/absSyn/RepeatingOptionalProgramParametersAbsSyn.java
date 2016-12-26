package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalProgramParametersAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> OptionalFlowModeConcSyn;
    private final List<IAbsSyn> OptionalChangeModeConcSyn;
    private final List<IAbsSyn> TypedIdentConcSyn;
    private final List<IAbsSyn> RepeatingOptionalParametersConcSyn;

    public RepeatingOptionalProgramParametersAbsSyn(IToken t, List<IAbsSyn> OptionalFlowModeConcSyn, List<IAbsSyn>  OptionalChangeModeConcSyn, List<IAbsSyn>  TypedIdentConcSyn, List<IAbsSyn>  RepeatingOptionalParametersConcSyn) {
        super(t);
        this.OptionalFlowModeConcSyn = OptionalFlowModeConcSyn;
        this.OptionalChangeModeConcSyn = OptionalChangeModeConcSyn;
        this.TypedIdentConcSyn = TypedIdentConcSyn;
        this.RepeatingOptionalParametersConcSyn = RepeatingOptionalParametersConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
