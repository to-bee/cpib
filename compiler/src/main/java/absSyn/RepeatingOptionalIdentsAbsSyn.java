package absSyn;

import scanner.errors.ContextError;
import scanner.token.Ident;

/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalIdentsAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    private IAbsSyn subType;

    public RepeatingOptionalIdentsAbsSyn(IAbsSyn subType) {

        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        subType.check();
    }
}
