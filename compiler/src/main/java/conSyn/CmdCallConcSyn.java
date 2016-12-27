package conSyn;

import absSyn.CmdAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdCallConcSyn extends AbstractConcSyn implements IConcSyn {
    public CmdCallConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdCallAbsSyn toAbsSyn() throws ContextError {
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        consume();
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
            parseNext(new ExpressionListConcSyn(getTokenList(), getCounter()));
            parseNext(new OptionalGlobalInitsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
