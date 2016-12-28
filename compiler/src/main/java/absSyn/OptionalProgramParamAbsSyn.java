package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalProgramParamAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private IAbsSyn subType;

    public OptionalProgramParamAbsSyn(IAbsSyn subType) {

        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }
}
