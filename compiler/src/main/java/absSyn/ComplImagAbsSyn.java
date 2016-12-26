package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ComplImagAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> ExpressionConcSyn;

    public ComplImagAbsSyn(IToken t, List<IAbsSyn> ExpressionConcSyn) {
        super(t);
        this.ExpressionConcSyn = ExpressionConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
