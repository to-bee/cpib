package absSyn;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalDeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    private final DeclarationAbsSyn declarationAbsSyn;
    private final RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn;

    public RepeatingOptionalDeclarationsAbsSyn(DeclarationAbsSyn declarationAbsSyn, RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn) {
        this.declarationAbsSyn = declarationAbsSyn;
        this.repeatingOptionalDeclarationsAbsSyn = repeatingOptionalDeclarationsAbsSyn;
    }

    @Override
    public void check() {
        this.declarationAbsSyn.check();
        this.repeatingOptionalDeclarationsAbsSyn.check();
    }
}

