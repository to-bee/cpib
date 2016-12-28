package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalProgramParametersAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {

    private final OptionalFlowModeAbsSyn optionalFlowModeAbsSyn;
    private final OptionalChangeModeAbsSyn optionalChangeModeAbsSyn;
    private final TypedIdentAbsSyn typedIdentAbsSyn;
    private final RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn;

    public RepeatingOptionalProgramParametersAbsSyn1(OptionalFlowModeAbsSyn optionalFlowModeAbsSyn, OptionalChangeModeAbsSyn optionalChangeModeAbsSyn, TypedIdentAbsSyn typedIdentAbsSyn, RepeatingOptionalParametersAbsSyn repeatingOptionalParametersAbsSyn) {

        this.optionalFlowModeAbsSyn = optionalFlowModeAbsSyn;
        this.optionalChangeModeAbsSyn = optionalChangeModeAbsSyn;
        this.typedIdentAbsSyn = typedIdentAbsSyn;
        this.repeatingOptionalParametersAbsSyn = repeatingOptionalParametersAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        optionalFlowModeAbsSyn.check();
        optionalChangeModeAbsSyn.check();
        typedIdentAbsSyn.check();
        repeatingOptionalParametersAbsSyn.check();
    }
}
