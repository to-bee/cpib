package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;

/**
 * Created by ylaub on 26.12.2016.
 */
public class TypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Ident ident;
    private final SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn1;
    private final SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn2;
    private final OptionalTypeDeclarationAbsSyn optionalTypeDeclarationAbsSyn;

    public TypeDeclarationAbsSyn(Ident ident, SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn1, SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn2, OptionalTypeDeclarationAbsSyn optionalTypeDeclarationAbsSyn) {
        this.ident = ident;
        this.subTypeDeclarationAbsSyn1 = subTypeDeclarationAbsSyn1;
        this.subTypeDeclarationAbsSyn2 = subTypeDeclarationAbsSyn2;
        this.optionalTypeDeclarationAbsSyn = optionalTypeDeclarationAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        if(this.ident != null) {
            this.ident.check();
        }
        this.subTypeDeclarationAbsSyn1.check();
        this.subTypeDeclarationAbsSyn2.check();
        this.optionalTypeDeclarationAbsSyn.check();
    }
}
