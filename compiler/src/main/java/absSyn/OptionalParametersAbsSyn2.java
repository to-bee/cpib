package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalParametersAbsSyn2 extends AbstractAbsSyn implements IAbsSyn {

    private final ParameterAbsSyn parameterAbsSyn;
    private final RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn;

    public OptionalParametersAbsSyn2(ParameterAbsSyn parameterAbsSyn, RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn) {

        this.parameterAbsSyn = parameterAbsSyn;
        this.repeatingOptionalParametersAbsSyn = repeatingOptionalParametersAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        parameterAbsSyn.check();
        repeatingOptionalParametersAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
