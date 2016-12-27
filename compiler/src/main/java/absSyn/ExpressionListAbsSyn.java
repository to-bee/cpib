package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ExpressionListAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final OptionalExpressionsAbsSyn optionalExpressionsAbsSyn;

    public ExpressionListAbsSyn(OptionalExpressionsAbsSyn optionalExpressionsAbsSyn) {
        this.optionalExpressionsAbsSyn = optionalExpressionsAbsSyn;
    }

    @Override
    public void check() {
        optionalExpressionsAbsSyn.check();
    }
}
