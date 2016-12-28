package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalParametersAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final ParameterAbsSyn parameterAbsSyn;
    private final RepeatingOptionalParametersAbsSyn1 repeatingOptionalParametersAbsSyn1;

    public RepeatingOptionalParametersAbsSyn1(ParameterAbsSyn parameterAbsSyn, RepeatingOptionalParametersAbsSyn1 repeatingOptionalParametersAbsSyn1) {
        this.parameterAbsSyn = parameterAbsSyn;
        this.repeatingOptionalParametersAbsSyn1 = repeatingOptionalParametersAbsSyn1;
    }

    @Override
    public void check() throws ContextError {
        parameterAbsSyn.check();
        repeatingOptionalParametersAbsSyn1.check();
    }
}
