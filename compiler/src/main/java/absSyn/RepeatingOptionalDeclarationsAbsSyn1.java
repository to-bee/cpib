package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class RepeatingOptionalDeclarationsAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private final DeclarationAbsSyn declarationAbsSyn;
    private final RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn;

    public RepeatingOptionalDeclarationsAbsSyn1(DeclarationAbsSyn declarationAbsSyn, RepeatingOptionalDeclarationsAbsSyn repeatingOptionalDeclarationsAbsSyn) {

        this.declarationAbsSyn = declarationAbsSyn;
        this.repeatingOptionalDeclarationsAbsSyn = repeatingOptionalDeclarationsAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        declarationAbsSyn.check();
        repeatingOptionalDeclarationsAbsSyn.check();
    }
}
