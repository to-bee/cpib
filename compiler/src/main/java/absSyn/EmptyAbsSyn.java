package absSyn;

import scanner.errors.ContextError;

/**
 * Created by ylaub on 26.12.2016.
 */
public class EmptyAbsSyn extends AbstractAbsSyn implements IAbsSyn {


    @Override
    public void check() throws ContextError {
        //nothing to check
    }
}
