package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

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

    public String toString(int counter) {
        return "RepeatingOptionalProgramParametersAbsSyn1:\r\n\t" + optionalFlowModeAbsSyn.toString(counter) + "," + typedIdentAbsSyn.toString(counter) + optionalFlowModeAbsSyn.toString(counter) + "," + repeatingOptionalParametersAbsSyn.toString(counter);
    }


    @Override
    public void check() throws ContextError {
        optionalFlowModeAbsSyn.check();
        optionalChangeModeAbsSyn.check();
        typedIdentAbsSyn.check();
        repeatingOptionalParametersAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
