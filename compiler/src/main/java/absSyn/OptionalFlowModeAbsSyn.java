package absSyn;

import scanner.errors.ContextError;
import scanner.token.IToken;

import java.util.List;

/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalFlowModeAbsSyn extends AbstractAbsSyn implements IAbsSyn{

    public OptionalFlowModeAbsSyn() {
    }

    @Override
    public void check() throws ContextError {
        //nothing to check
    }
}
