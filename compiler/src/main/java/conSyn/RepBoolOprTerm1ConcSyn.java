package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepBoolOprTerm1AbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepBoolOprTerm1ConcSyn extends AbstractConcSyn implements IConcSyn {
    private Term1ConcSyn term1ConcSyn;
    private RepBoolOprTerm1ConcSyn repBoolOprTerm1ConcSyn;

    public RepBoolOprTerm1ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepBoolOprTerm1AbsSyn toAbsSyn() throws ContextError {
        return new RepBoolOprTerm1AbsSyn(term1ConcSyn.toAbsSyn(), repBoolOprTerm1ConcSyn.toAbsSyn());
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
                || getTokenList().getCurrent().getTerminal() == Terminal.BECOMES) {

        } else if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.BOOLOPR) {
            consume();

            term1ConcSyn = new Term1ConcSyn(getTokenList(), getCounter());
            parseNext(term1ConcSyn);

            repBoolOprTerm1ConcSyn = new RepBoolOprTerm1ConcSyn(getTokenList(), getCounter());
            parseNext(repBoolOprTerm1ConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
