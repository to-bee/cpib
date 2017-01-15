package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalTypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private IAbsSyn subType;

    public OptionalTypeDeclarationAbsSyn(IAbsSyn subType) {

        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        //TODO Leftsidetype liste abfüllen
        subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
