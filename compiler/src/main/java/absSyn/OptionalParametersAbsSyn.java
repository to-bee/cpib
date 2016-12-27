package absSyn;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalParametersAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final ParameterAbsSyn parameterAbsSyn;
    private final RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn;

    public OptionalParametersAbsSyn(ParameterAbsSyn parameterAbsSyn, RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn) {
        this.parameterAbsSyn = parameterAbsSyn;
        this.repeatingOptionalParametersAbsSyn = repeatingOptionalParametersAbsSyn;
    }

    @Override
    public void check() {
        parameterAbsSyn.check();
        repeatingOptionalParametersAbsSyn.check();
    }
}
