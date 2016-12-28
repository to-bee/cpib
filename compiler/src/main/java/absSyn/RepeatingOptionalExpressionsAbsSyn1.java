package absSyn;

import scanner.errors.ContextError;

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

    @Override
    public void check() throws ContextError {
        expressionAbsSyn.check();
        repeatingOptionalExpressionsAbsSyn.check();
    }
}
