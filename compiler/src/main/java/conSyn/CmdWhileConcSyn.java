package conSyn;

import absSyn.CmdWhileAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdWhileConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionConcSyn exprWhile;
    private BlockCmdConcSyn cmdWhile;
    private Terminal terminal;

    public CmdWhileConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdWhileAbsSyn toAbsSyn() throws ContextError {
        return new CmdWhileAbsSyn(exprWhile.toAbsSyn(), cmdWhile.toAbsSyn(), terminal);
    }

    @Override
    public void parse() throws GrammarError {
        consume();
        this.terminal = getTokenList().getCurrent().getTerminal();
        exprWhile = new ExpressionConcSyn(getTokenList(), getCounter());
        parseNext(exprWhile);
        if (this.terminal == Terminal.DO) {
            consume();
            cmdWhile = new BlockCmdConcSyn(getTokenList(), getCounter());
            parseNext(cmdWhile);
            this.terminal = getTokenList().getCurrent().getTerminal();
            if (this.terminal == Terminal.ENDWHILE) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
