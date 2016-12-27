package conSyn;

import absSyn.FactorIdentAbsSyn;
import absSyn.FactorLiteralAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorIdentConcSyn extends AbstractConcSyn implements IConcSyn {
    private Ident ident;
    private OptionalIdentConcSyn optionalIdentConcSyn;

    public FactorIdentConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorIdentAbsSyn toAbsSyn() throws ContextError {
        return new FactorIdentAbsSyn(ident, optionalIdentConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        ident = (Ident) this.getTokenList().getCurrent();
        consume();
        optionalIdentConcSyn = new OptionalIdentConcSyn(getTokenList(), getCounter());
        parseNext(optionalIdentConcSyn);
    }
}
