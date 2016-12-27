package absSyn;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalTypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn;

    public OptionalTypeDeclarationAbsSyn(SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn) {
        this.subTypeDeclarationAbsSyn = subTypeDeclarationAbsSyn;
    }

    @Override
    public void check() {
        subTypeDeclarationAbsSyn.check();
    }
}
