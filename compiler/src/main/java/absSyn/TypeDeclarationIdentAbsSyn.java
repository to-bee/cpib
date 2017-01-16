package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class TypeDeclarationIdentAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private Ident ident;

    public TypeDeclarationIdentAbsSyn(Ident ident) {
        this.ident = ident;
    }
    public String toString(int counter) {
        return "TypeDeclarationIdentAbsSyn:\r\n\t" + this.ident;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
