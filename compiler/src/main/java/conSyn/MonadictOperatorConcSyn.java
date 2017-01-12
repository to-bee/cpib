package conSyn;

import absSyn.MonadictOperatorAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class MonadictOperatorConcSyn extends AbstractConcSyn implements IConcSyn {
    private IToken token;
    private Terminal terminal;

    public MonadictOperatorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public MonadictOperatorAbsSyn toAbsSyn() throws ContextError {
        return new MonadictOperatorAbsSyn(token);
    }


    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.COMPLEMENT
                || this.terminal == Terminal.ADDOPR
                || this.terminal == Terminal.MINOPR
                || this.terminal == Terminal.MULTOPR
                || this.terminal == Terminal.DIVOPR
                || this.terminal == Terminal.MINOPR) {
            this.token = this.getTokenList().getCurrent();
            consume();
        } else {
            throwGrammarError();
        }
    }
}
