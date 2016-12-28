package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class OptionalTypeDeclarationAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {

    private SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn;

    public OptionalTypeDeclarationAbsSyn1(SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn) {

        this.subTypeDeclarationAbsSyn = subTypeDeclarationAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        subTypeDeclarationAbsSyn.check();
    }
}
