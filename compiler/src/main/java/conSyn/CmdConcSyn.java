package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionConcSyn expressionConcSyn1;
    private ExpressionConcSyn expressionConcSyn2;
    private IToken token;

    public CmdConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdAbsSyn toAbsSyn() throws ContextError {
        return new CmdAbsSyn(token, ExpressionConcSyn, BlockCmdConcSyn, ExpressionListConcSyn, OptionalGlobalInitsConcSyn);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.SKIP) {
            parseNext(new CmdSkipConcSyn(getTokenList(), getCounter()));

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IF) {
            parseNext(new CmdIfConcSyn(getTokenList(), getCounter()));

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.WHILE) {
            parseNext(new CmdWhileConcSyn(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.CALL) {
            parseNext(new CmdCallConcSyn(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
                || getTokenList().getCurrent().getTerminal() == Terminal.REAL
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAG
                || getTokenList().getCurrent().getTerminal() == Terminal.LPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {

            expressionConcSyn1 = new ExpressionConcSyn(getTokenList(), getCounter());
            parseNext(expressionConcSyn1);
            if (getTokenList().getCurrent().getTerminal() == Terminal.BECOMES) {
                consume();
                expressionConcSyn2 = new ExpressionConcSyn(getTokenList(), getCounter());
                parseNext(expressionConcSyn2);
            } else {
                throwGrammarError();
            }
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.DEBUGIN
                || getTokenList().getCurrent().getTerminal() == Terminal.DEBUGOUT) {
            consume();
            parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
