package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ParameterAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final OptionalFlowModeAbsSyn optionalFlowModeAbsSyn;
    private final OptionalMechModeAbsSyn optionalMechModeAbsSyn;
    private final StorageDeclarationAbsSyn storageDeclarationAbsSyn;
    private final Terminal terminal;

    public ParameterAbsSyn(OptionalFlowModeAbsSyn optionalFlowModeAbsSyn, OptionalMechModeAbsSyn optionalMechModeAbsSyn, StorageDeclarationAbsSyn storageDeclarationAbsSyn, Terminal terminal) {

        this.optionalFlowModeAbsSyn = optionalFlowModeAbsSyn;
        this.optionalMechModeAbsSyn = optionalMechModeAbsSyn;
        this.storageDeclarationAbsSyn = storageDeclarationAbsSyn;
        this.terminal = terminal;
    }
    public String toString(int counter) {
        return "ParameterAbsSyn:\r\n\t" + optionalFlowModeAbsSyn.toString(counter)
                + optionalMechModeAbsSyn.toString(counter)
                + storageDeclarationAbsSyn.toString(counter)
                + "," + this.terminal;
    }

    @Override
    public void check() throws ContextError {
        this.optionalFlowModeAbsSyn.check();
        this.optionalMechModeAbsSyn.check();
        this.storageDeclarationAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
