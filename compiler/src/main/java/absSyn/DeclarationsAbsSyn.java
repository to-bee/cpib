package absSyn;
/**
 * Created by ylaub on 26.12.2016.
 */
public class DeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final DeclarationAbsSyn declarationAbsSyn;
    private final RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn;

    public DeclarationsAbsSyn(DeclarationAbsSyn declarationAbsSyn, RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn) {
        this.declarationAbsSyn = declarationAbsSyn;
        this.repeatingOptionalDeclarationsAbsSyn = repeatingOptionalDeclarationsAbsSyn;
    }

    @Override
    public void check() {
        declarationAbsSyn.check();
        repeatingOptionalDeclarationsAbsSyn.check();
    }
}
