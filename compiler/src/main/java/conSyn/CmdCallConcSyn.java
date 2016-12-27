package conSyn;

import absSyn.CmdCallAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdCallConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionListConcSyn expressionListConcSyn;
    private OptionalGlobalInitsConcSyn optionalGlobalInitsConcSyn;

    public CmdCallConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdCallAbsSyn toAbsSyn() throws ContextError {
        return new CmdCallAbsSyn(expressionListConcSyn.toAbsSyn(), optionalGlobalInitsConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        consume();
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
            expressionListConcSyn= new ExpressionListConcSyn(getTokenList(), getCounter());
            parseNext(expressionListConcSyn);
            optionalGlobalInitsConcSyn= new OptionalGlobalInitsConcSyn(getTokenList(), getCounter());
            parseNext(optionalGlobalInitsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
