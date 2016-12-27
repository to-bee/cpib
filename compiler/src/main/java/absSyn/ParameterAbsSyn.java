package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ParameterAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final OptionalFlowModeAbsSyn optionalFlowModeAbsSyn;
    private final OptionalMechModeAbsSyn optionalMechModeAbsSyn;
    private final StorageDeclarationAbsSyn storageDeclarationAbsSyn;

    public ParameterAbsSyn(OptionalFlowModeAbsSyn optionalFlowModeAbsSyn, OptionalMechModeAbsSyn optionalMechModeAbsSyn, StorageDeclarationAbsSyn storageDeclarationAbsSyn) {
        this.optionalFlowModeAbsSyn = optionalFlowModeAbsSyn;
        this.optionalMechModeAbsSyn = optionalMechModeAbsSyn;
        this.storageDeclarationAbsSyn = storageDeclarationAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        optionalFlowModeAbsSyn.check();
        optionalMechModeAbsSyn.check();
        storageDeclarationAbsSyn.check();
    }
}
