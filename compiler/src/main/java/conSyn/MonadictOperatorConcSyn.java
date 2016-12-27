package conSyn;

import absSyn.MonadictOperatorAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class MonadictOperatorConcSyn extends AbstractConcSyn implements IConcSyn {
    public MonadictOperatorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public MonadictOperatorAbsSyn toAbsSyn() throws ContextError {
        return new MonadictOperatorAbsSyn();
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR) {
            consume();
        } else {
            throwGrammarError();
        }
    }
}
