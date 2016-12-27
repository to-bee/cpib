package absSyn;

import scanner.datatypes.Terminal;

/**
 * Created by ylaub on 26.12.2016.
 */
public class SubTypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private Terminal type;
    private final SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn1;
    private final SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn2;
    private final OptionalTypeDeclarationAbsSyn optionalTypeDeclarationAbsSyn;

    public SubTypeDeclarationAbsSyn(Terminal type, SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn1, SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn2, OptionalTypeDeclarationAbsSyn optionalTypeDeclarationAbsSyn) {
        this.type = type;
        this.subTypeDeclarationAbsSyn1 = subTypeDeclarationAbsSyn1;
        this.subTypeDeclarationAbsSyn2 = subTypeDeclarationAbsSyn2;
        this.optionalTypeDeclarationAbsSyn = optionalTypeDeclarationAbsSyn;
    }

    @Override
    public void check() {
        //TODO: type checken
        this.subTypeDeclarationAbsSyn1.check();
        this.subTypeDeclarationAbsSyn2.check();
        this.optionalTypeDeclarationAbsSyn.check();
    }
}

