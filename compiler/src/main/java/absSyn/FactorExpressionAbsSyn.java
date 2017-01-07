package absSyn;

import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class FactorExpressionAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private IToken token;
    private final ExpressionAbsSyn expressionAbsSyn;
    private final RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn;

    public IToken getToken() {
        return token;
    }

    public ExpressionAbsSyn getExpressionAbsSyn() {
        return expressionAbsSyn;
    }

    public RepeatingOptionalExpressionsAbsSyn getRepeatingOptionalExpressionsAbsSyn() {
        return repeatingOptionalExpressionsAbsSyn;
    }

    public FactorExpressionAbsSyn(IToken token, ExpressionAbsSyn expressionAbsSyn, RepeatingOptionalExpressionsAbsSyn repeatingOptionalExpressionsAbsSyn) {
        this.token = token;

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
