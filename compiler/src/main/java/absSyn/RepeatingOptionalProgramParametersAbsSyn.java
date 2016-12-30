package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalProgramParametersAbsSyn extends AbstractAbsSyn implements IAbsSyn {

    private IAbsSyn subType;

    public RepeatingOptionalProgramParametersAbsSyn(IAbsSyn subType) {

        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
