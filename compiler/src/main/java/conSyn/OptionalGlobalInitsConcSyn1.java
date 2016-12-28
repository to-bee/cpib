package conSyn;

import absSyn.OptionalGlobalInitsAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalInitsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private IdentsConcSyn identsConcSyn;

    public OptionalGlobalInitsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalGlobalInitsAbsSyn1 toAbsSyn() throws ContextError {
        return new OptionalGlobalInitsAbsSyn1(identsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        identsConcSyn = new IdentsConcSyn(getTokenList(), getCounter());
        parseNext(identsConcSyn);
    }
}
