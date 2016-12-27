package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalParametersAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    public OptionalParametersAbsSyn(ParameterAbsSyn parameterAbsSyn, RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn) {

    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
