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

    public MonadictOperatorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public MonadictOperatorAbsSyn toAbsSyn() throws ContextError {
        return new MonadictOperatorAbsSyn(token);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MULTOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.DIVOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR) {
            this.token = this.getTokenList().getCurrent();
            consume();
        } else {
            throwGrammarError();
        }
    }
}
