package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
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
    public CmdConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public CmdAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        //TODO check wenn command = while --> dann cmdwhile abssyn
        List<IAbsSyn> ExpressionConcSyn = super.getListByType(ExpressionConcSyn.class);
        List<IAbsSyn> BlockCmdConcSyn = super.getListByType(BlockCmdConcSyn.class);
        List<IAbsSyn> ExpressionListConcSyn = super.getListByType(ExpressionListConcSyn.class);
        List<IAbsSyn> OptionalGlobalInitsConcSyn = super.getListByType(OptionalGlobalInitsConcSyn.class);

        return new CmdAbsSyn(token, ExpressionConcSyn, BlockCmdConcSyn, ExpressionListConcSyn, OptionalGlobalInitsConcSyn);
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
            //TODO: cmdwhile aufrufen: concrete syntax
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
