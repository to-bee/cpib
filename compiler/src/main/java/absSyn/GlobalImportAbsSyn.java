package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class GlobalImportAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> OptionalFlowModeConcSyn;
    private final List<IAbsSyn> OptionalChangeModeConcSyn;

    public GlobalImportAbsSyn(IToken t, List<IAbsSyn> OptionalFlowModeConcSyn, List<IAbsSyn> OptionalChangeModeConcSyn) {
        super(t);
        this.OptionalFlowModeConcSyn = OptionalFlowModeConcSyn;
        this.OptionalChangeModeConcSyn = OptionalChangeModeConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
