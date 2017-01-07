package conSyn;

import absSyn.CmdSkipAbsSyn;
import absSyn.FactorLiteralAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorLiteralConcSyn extends AbstractConcSyn implements IConcSyn {
    private IToken token;

    public FactorLiteralConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    public IToken getToken() {
        return token;
    }

    @Override
    public FactorLiteralAbsSyn toAbsSyn() throws ContextError {
        return new FactorLiteralAbsSyn(token);
    }

    @Override
    public void parse() throws GrammarError {
        this.token = this.getTokenList().getCurrent();
        consume();
    }
}
