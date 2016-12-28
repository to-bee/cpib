package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

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

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
