package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalIdentAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private ExpressionListAbsSyn expressionListAbsSyn;

    public OptionalIdentAbsSyn(ExpressionListAbsSyn expressionListAbsSyn) {
        this.expressionListAbsSyn = expressionListAbsSyn;
    }
    @Override
    public void check() throws ContextError {
        expressionListAbsSyn.check();
    }
}
