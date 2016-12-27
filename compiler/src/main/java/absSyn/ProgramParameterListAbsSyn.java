package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class ProgramParameterListAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final OptionalProgramParamAbsSyn optionalProgramParamConcSyn;

    public ProgramParameterListAbsSyn(OptionalProgramParamAbsSyn optionalProgramParamConcSyn) {
        this.optionalProgramParamConcSyn = optionalProgramParamConcSyn;
    }

    @Override
    public void check() throws ContextError {
        optionalProgramParamConcSyn.check();
    }
}
