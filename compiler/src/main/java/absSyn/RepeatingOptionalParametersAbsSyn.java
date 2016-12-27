package absSyn;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalParametersAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final ParameterAbsSyn parameterAbsSyn;
    private final RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn;

    public RepeatingOptionalParametersAbsSyn(ParameterAbsSyn parameterAbsSyn, RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn) {

        this.parameterAbsSyn = parameterAbsSyn;
        this.repeatingOptionalParametersAbsSyn = repeatingOptionalParametersAbsSyn;
    }

    @Override
    public void check() {
        this.parameterAbsSyn.check();
        this.repeatingOptionalParametersAbsSyn.check();
    }
}
