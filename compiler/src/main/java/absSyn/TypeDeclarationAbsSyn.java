package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class TypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    public IAbsSyn getSubType() {
        return subType;
    }

    private IAbsSyn subType;

    public TypeDeclarationAbsSyn(IAbsSyn subType) {

        this.subType = subType;
    }
    public String toString(int counter) {
        return "TypeDeclarationAbsSyn:\r\n\t" + subType.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location; // nothing to do
    }
}