package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class CmdCallAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final ExpressionListAbsSyn expressionListAbsSyn;
    private final OptionalGlobalInitsAbsSyn optionalGlobalInitsAbsSyn;

    public CmdCallAbsSyn(ExpressionListAbsSyn expressionListAbsSyn, OptionalGlobalInitsAbsSyn optionalGlobalInitsAbsSyn) {

        this.expressionListAbsSyn = expressionListAbsSyn;
        this.optionalGlobalInitsAbsSyn = optionalGlobalInitsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        expressionListAbsSyn.check();
        optionalGlobalInitsAbsSyn.check();
    }
}
