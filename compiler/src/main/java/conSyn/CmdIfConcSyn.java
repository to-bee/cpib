package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdIfConcSyn extends AbstractConcSyn implements IConcSyn {
    public CmdIfConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdIfAbsSyn toAbsSyn() throws ContextError {
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        consume();
        parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
        if (getTokenList().getCurrent().getTerminal() == Terminal.THEN) {
            consume();
            parseNext(new BlockCmdConcSyn(getTokenList(), getCounter()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.ELSE) {
                consume();
                parseNext(new BlockCmdConcSyn(getTokenList(), getCounter()));
                if (getTokenList().getCurrent().getTerminal() == Terminal.ENDIF) {
                    consume();

                } else {
                    throwGrammarError();
                }
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
