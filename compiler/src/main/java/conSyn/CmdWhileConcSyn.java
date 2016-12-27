package conSyn;

import absSyn.CmdAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdWhileConcSyn extends AbstractConcSyn implements IConcSyn {
    public CmdWhileConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdWhileAbsSyn toAbsSyn() throws ContextError {
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        consume();
        parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
            consume();
            parseNext(new BlockCmdConcSyn(getTokenList(), getCounter()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.ENDWHILE) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
        //TODO: cmdwhile aufrufen: concrete syntax
    }
}
