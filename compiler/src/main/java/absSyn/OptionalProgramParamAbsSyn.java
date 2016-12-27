package absSyn;

import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;

import java.util.List;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalProgramParamAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private final OptionalFlowModeAbsSyn optionalFlowModeAbsSyn;
    private final OptionalChangeModeAbsSyn optionalChangeModeAbsSyn;
    private final TypedIdentAbsSyn typedIdentAbsSyn;
    private final RepeatingOptionalProgramParametersAbsSyn repeatingOptionalProgramParametersAbsSyn;

    public OptionalProgramParamAbsSyn(OptionalFlowModeAbsSyn optionalFlowModeAbsSyn, OptionalChangeModeAbsSyn optionalChangeModeAbsSyn, TypedIdentAbsSyn typedIdentAbsSyn, RepeatingOptionalProgramParametersAbsSyn repeatingOptionalProgramParametersAbsSyn) {
        this.optionalFlowModeAbsSyn = optionalFlowModeAbsSyn;
        this.optionalChangeModeAbsSyn = optionalChangeModeAbsSyn;
        this.typedIdentAbsSyn = typedIdentAbsSyn;
        this.repeatingOptionalProgramParametersAbsSyn = repeatingOptionalProgramParametersAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        if (optionalFlowModeAbsSyn != null) {
            this.optionalFlowModeAbsSyn.check();
            optionalChangeModeAbsSyn.check();
            typedIdentAbsSyn.check();
            repeatingOptionalProgramParametersAbsSyn.check();
        }
    }
}
