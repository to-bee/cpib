package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;

/**
 * Created by ylaub on 28.12.2016.
 */
public class TypeDeclarationIdentAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private Ident ident;

    public TypeDeclarationIdentAbsSyn(Ident ident) {
        this.ident = ident;
    }

    @Override
    public void check() throws ContextError {
        ident.check();
    }
}
