package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalIdentAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private ExpressionListAbsSyn expressionListAbsSyn;

    public OptionalIdentAbsSyn1(ExpressionListAbsSyn expressionListAbsSyn) {
        this.expressionListAbsSyn = expressionListAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        expressionListAbsSyn.check();
    }
}
