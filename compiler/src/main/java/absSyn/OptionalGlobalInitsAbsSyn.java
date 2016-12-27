package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalGlobalInitsAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private IdentsAbsSyn identsAbsSyn;

    public OptionalGlobalInitsAbsSyn(IdentsAbsSyn identsAbsSyn) {
        this.identsAbsSyn = identsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        identsAbsSyn.check();
    }
}
