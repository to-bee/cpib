package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ParameterListAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private OptionalParametersAbsSyn optionalParametersAbsSyn;

    public ParameterListAbsSyn(OptionalParametersAbsSyn optionalParametersAbsSyn) {

        this.optionalParametersAbsSyn = optionalParametersAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        this.optionalParametersAbsSyn.check();
    }
}
