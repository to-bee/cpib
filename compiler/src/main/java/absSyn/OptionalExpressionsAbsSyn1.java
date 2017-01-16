package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalExpressionsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final ExpressionAbsSyn expressionAbsSyn;
    private final RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn;

    public OptionalExpressionsAbsSyn1(ExpressionAbsSyn expressionAbsSyn, RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn) {

        this.expressionAbsSyn = expressionAbsSyn;
        this.repeatingOptionalExpressionsAbsSyn = repeatingOptionalExpressionsAbsSyn;
    }

    public String toString(int counter) {
        return "OptionalExpressionsAbsSyn1:\r\n\t" + this.expressionAbsSyn.toString(counter)
                + "," + this.repeatingOptionalExpressionsAbsSyn.toString(counter);
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
