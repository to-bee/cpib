package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.OptionalIdentAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalIdentConcSyn extends AbstractConcSyn implements IConcSyn {
    private ExpressionListConcSyn expressionListConcSyn;

    public OptionalIdentConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalIdentAbsSyn toAbsSyn()throws ContextError {
        return new OptionalIdentAbsSyn(expressionListConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.COMMA
                || getTokenList().getCurrent().getTerminal() == Terminal.DO
                || getTokenList().getCurrent().getTerminal() == Terminal.THEN
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDPROC
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDFUN
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDWHILE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDIF
                || getTokenList().getCurrent().getTerminal() == Terminal.ELSE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDPROGRAM
                || getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON
                || getTokenList().getCurrent().getTerminal() == Terminal.BECOMES
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.BOOLOPR
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.RELOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MULTOPR) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.INIT) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            expressionListConcSyn = new ExpressionListConcSyn(getTokenList(), getCounter());
            parseNext(expressionListConcSyn);

        } else {
            throwGrammarError();
        }
    }
}
