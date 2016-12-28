package absSyn;

import scanner.errors.ContextError;

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
}
