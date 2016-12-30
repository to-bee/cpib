package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class GlobalImportAbsSyn extends AbstractAbsSyn implements IAbsSyn{


    private final Ident ident;
    private final OptionalFlowModeAbsSyn optionalFlowModeAbsSyn;
    private final OptionalChangeModeAbsSyn optionalChangeModeAbsSyn;

    public GlobalImportAbsSyn(Ident ident, OptionalFlowModeAbsSyn optionalFlowModeAbsSyn, OptionalChangeModeAbsSyn optionalChangeModeAbsSyn) {

        this.ident = ident;
        this.optionalFlowModeAbsSyn = optionalFlowModeAbsSyn;
        this.optionalChangeModeAbsSyn = optionalChangeModeAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
        optionalFlowModeAbsSyn.check();
        optionalChangeModeAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
