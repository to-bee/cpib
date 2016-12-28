package absSyn;

import scanner.errors.ContextError;
import scanner.token.IToken;

/**
 * Created by ylaub on 28.12.2016.
 */
public class TypeDeclarationTypeAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private IToken type;

    public TypeDeclarationTypeAbsSyn(IToken type) {
        this.type = type;
    }

    @Override
    public void check() throws ContextError {
        //todo Type Check

    }
}
