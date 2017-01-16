package absSyn;

import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorExpressionAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private final ExpressionAbsSyn expressionAbsSyn;
    private final RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn;

    public ExpressionAbsSyn getExpressionAbsSyn() {
        return expressionAbsSyn;
    }

    public RepeatingOptionalExpressionsAbsSyn getRepeatingOptionalExpressionsAbsSyn() {
        return repeatingOptionalExpressionsAbsSyn;
    }

    public FactorExpressionAbsSyn(ExpressionAbsSyn expressionAbsSyn, RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn) {
        this.expressionAbsSyn = expressionAbsSyn;
        this.repeatingOptionalExpressionsAbsSyn = repeatingOptionalExpressionsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        this.expressionAbsSyn.check();
        this.repeatingOptionalExpressionsAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
