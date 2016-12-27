package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepAddOprTerm3AbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepAddOprTerm3ConcSyn extends AbstractConcSyn implements IConcSyn {
    private Term3ConcSyn term3ConcSyn;
    private RepAddOprTerm3ConcSyn repAddOprTerm3ConcSyn;

    public RepAddOprTerm3ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepAddOprTerm3AbsSyn toAbsSyn() throws ContextError {
        return new RepAddOprTerm3AbsSyn(term3ConcSyn.toAbsSyn(), repAddOprTerm3ConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR) {
            consume();

            term3ConcSyn = new Term3ConcSyn(getTokenList(), getCounter());
            parseNext(term3ConcSyn);

            repAddOprTerm3ConcSyn = new RepAddOprTerm3ConcSyn(getTokenList(), getCounter());
            parseNext(repAddOprTerm3ConcSyn);
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN
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
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.RELOPR) {

        } else {
            throwGrammarError();
        }
    }
}
