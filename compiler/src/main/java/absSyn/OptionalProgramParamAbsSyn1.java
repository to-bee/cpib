package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalProgramParamAbsSyn1  extends AbstractAbsSyn implements IAbsSyn {
    private final OptionalFlowModeAbsSyn optionalFlowModeAbsSyn;
    private final OptionalChangeModeAbsSyn optionalChangeModeAbsSyn;
    private final TypedIdentAbsSyn typedIdentAbsSyn;
    private final RepeatingOptionalProgramParametersAbsSyn repeatingOptionalProgramParametersAbsSyn;

    public OptionalProgramParamAbsSyn1(OptionalFlowModeAbsSyn optionalFlowModeAbsSyn, OptionalChangeModeAbsSyn optionalChangeModeAbsSyn, TypedIdentAbsSyn typedIdentAbsSyn, RepeatingOptionalProgramParametersAbsSyn repeatingOptionalProgramParametersAbsSyn) {

        this.optionalFlowModeAbsSyn = optionalFlowModeAbsSyn;
        this.optionalChangeModeAbsSyn = optionalChangeModeAbsSyn;
        this.typedIdentAbsSyn = typedIdentAbsSyn;
        this.repeatingOptionalProgramParametersAbsSyn = repeatingOptionalProgramParametersAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        optionalFlowModeAbsSyn.check();
        optionalChangeModeAbsSyn.check();
        typedIdentAbsSyn.check();
        repeatingOptionalProgramParametersAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
