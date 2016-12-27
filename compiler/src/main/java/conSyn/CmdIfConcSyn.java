package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.CmdIfAbsSyn;
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
    private ExpressionConcSyn exprIf;
    private BlockCmdConcSyn cmdThen;
    private BlockCmdConcSyn cmdElse;

    public CmdIfConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdIfAbsSyn toAbsSyn() throws ContextError {
        return new CmdIfAbsSyn(exprIf.toAbsSyn(), cmdThen.toAbsSyn(),cmdElse.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        consume();
        exprIf = new ExpressionConcSyn(getTokenList(), getCounter());
        parseNext(exprIf);
        if (getTokenList().getCurrent().getTerminal() == Terminal.THEN) {
            consume();
            cmdThen = new BlockCmdConcSyn(getTokenList(), getCounter());
            parseNext(cmdThen);
            if (getTokenList().getCurrent().getTerminal() == Terminal.ELSE) {
                consume();
                cmdElse = new BlockCmdConcSyn(getTokenList(), getCounter());
                parseNext(cmdElse);
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
