package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalParametersAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {

    private final Ident ident;
    private final ParameterAbsSyn parameterAbsSyn;
    private final RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn;

    public OptionalParametersAbsSyn1(Ident ident, ParameterAbsSyn parameterAbsSyn, RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn) {

        this.ident = ident;
        this.parameterAbsSyn = parameterAbsSyn;
        this.repeatingOptionalParametersAbsSyn = repeatingOptionalParametersAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
        parameterAbsSyn.check();
        repeatingOptionalParametersAbsSyn.check();
    }
}
