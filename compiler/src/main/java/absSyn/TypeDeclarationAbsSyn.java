package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;

/**
 * Created by ylaub on 26.12.2016.
 */
public class TypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private IAbsSyn subType;

    public TypeDeclarationAbsSyn(IAbsSyn subType) {

        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }
}