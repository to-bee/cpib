package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalExpressionsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final ExpressionAbsSyn expressionAbsSyn;
    private final RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn;

    public RepeatingOptionalExpressionsAbsSyn1(ExpressionAbsSyn expressionAbsSyn, RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn) {

        this.expressionAbsSyn = expressionAbsSyn;
        this.repeatingOptionalExpressionsAbsSyn = repeatingOptionalExpressionsAbsSyn;
    }

    public String toString(int counter) {
        return "RepeatingOptionalExpressionsAbsSyn1:\r\n\t" + expressionAbsSyn.toString(counter) + "," + repeatingOptionalExpressionsAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        expressionAbsSyn.check();
        repeatingOptionalExpressionsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
