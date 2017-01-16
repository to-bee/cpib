package absSyn;

import context.Context;
import context.VmVar;
import scanner.errors.ContextError;
import scanner.token.IToken;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class TypeDeclarationTypeAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    public IToken getToken() {
        return token;
    }

    private IToken token;

    public TypeDeclarationTypeAbsSyn(IToken token) {
        this.token = token;
    }
    public String toString(int counter) {
        return "TypeDeclarationTypeAbsSyn:\r\n\t" + token;
    }

    @Override
    public void check() throws ContextError {
        VmVar currentVmVar = Context.getCurrentVmVariable();
        currentVmVar.addType(this.token);
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location; // nothing to do
    }
}
