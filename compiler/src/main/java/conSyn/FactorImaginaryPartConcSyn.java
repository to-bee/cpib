package conSyn;

import absSyn.CmdSkipAbsSyn;
import absSyn.FactorImaginaryPartAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorImaginaryPartConcSyn extends AbstractConcSyn implements IConcSyn {
    private IToken token;

    public FactorImaginaryPartConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorImaginaryPartAbsSyn toAbsSyn() throws ContextError {
        return new FactorImaginaryPartAbsSyn(token);
    }

    public IToken getToken() {
        return token;
    }

    @Override
    public void parse() throws GrammarError {
        this.token = this.getTokenList().getCurrent();
        consume();
    }
}
