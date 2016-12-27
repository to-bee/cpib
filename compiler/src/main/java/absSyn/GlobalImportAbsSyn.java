package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import scanner.token.Ident;

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
    public void check() {
        ident.check();
        optionalFlowModeAbsSyn.check();
        optionalChangeModeAbsSyn.check();
    }
}
