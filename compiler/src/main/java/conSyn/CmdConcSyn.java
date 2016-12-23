package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdConcSyn extends AbstractConcSyn implements IConcSyn {
    public CmdConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.SKIP) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
                || getTokenList().getCurrent().getTerminal() == Terminal.REAL
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAG
                || getTokenList().getCurrent().getTerminal() == Terminal.LPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {
            parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.BECOMES) {
                consume();
                parseNext(new ExpressionConcSyn(getTokenList(), getCounter()));
            } else {
                throwGrammarError();
            }
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IF) {
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
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.WHILE) {
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
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.CALL) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();
                parseNext(new ExpressionListConcSyn(getTokenList(), getCounter()));
                parseNext(new OptionalGlobalInitsConcSyn(getTokenList(), getCounter()));
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
