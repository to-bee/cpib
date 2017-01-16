package absSyn;

import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class TypeDeclarationTypeAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    public IToken getType() {
        return type;
    }

    private IToken type;

    public TypeDeclarationTypeAbsSyn(IToken type) {
        this.type = type;
    }
    public String toString(int counter) {
        return "TypeDeclarationTypeAbsSyn:\r\n\t" + type;
    }

    @Override
    public void check() throws ContextError {
        //todo Type Check

    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location; // nothing to do
    }
}
