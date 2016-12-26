package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ParameterAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> OptionalFlowModeConcSyn;
    private final List<IAbsSyn> OptionalMechModeConcSyn;
    private final List<IAbsSyn> StorageDeclarationConcSyn;

    public ParameterAbsSyn(IToken t, List<IAbsSyn> OptionalFlowModeConcSyn, List<IAbsSyn> OptionalMechModeConcSyn, List<IAbsSyn> StorageDeclarationConcSyn) {
        super(t);
        this.OptionalFlowModeConcSyn = OptionalFlowModeConcSyn;
        this.OptionalMechModeConcSyn = OptionalMechModeConcSyn;
        this.StorageDeclarationConcSyn = StorageDeclarationConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
