package absSyn;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalExpressionsAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final ExpressionAbsSyn expressionAbsSyn;
    private final RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn;

    public RepeatingOptionalExpressionsAbsSyn(ExpressionAbsSyn expressionAbsSyn, RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn) {

        this.expressionAbsSyn = expressionAbsSyn;
        this.repeatingOptionalExpressionsAbsSyn = repeatingOptionalExpressionsAbsSyn;
    }

    @Override
    public void check() {
        this.expressionAbsSyn.check();
        this.repeatingOptionalExpressionsAbsSyn.check();
    }
}

