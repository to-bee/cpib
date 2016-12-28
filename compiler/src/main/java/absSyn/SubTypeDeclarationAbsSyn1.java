package absSyn;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;

/**
 * Created by ylaub on 28.12.2016.
 */
public class SubTypeDeclarationAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private Terminal type;

    public SubTypeDeclarationAbsSyn1(Terminal type) {

        this.type = type;
    }

    @Override
    public void check() throws ContextError {
        //TODO type check
    }
}
