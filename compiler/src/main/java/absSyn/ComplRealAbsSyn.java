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
public class ComplRealAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private IToken token;
    private final ExpressionAbsSyn expressionAbsSyn;

    public ComplRealAbsSyn(IToken token, ExpressionAbsSyn expressionAbsSyn) {
        this.token = token;
        this.expressionAbsSyn = expressionAbsSyn;
    }

    public String toString(int counter) {
        return "ComplRealAbsSyn:\r\n\t" + token.getTerminal()
                + "," + this.expressionAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        expressionAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
