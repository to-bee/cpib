package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

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

    public String toString(int counter) {
        return "cmdCallAbsSyn:\r\n\t" + expressionListAbsSyn.toString(counter) + "," + optionalGlobalInitsAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        expressionListAbsSyn.check();
        optionalGlobalInitsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
