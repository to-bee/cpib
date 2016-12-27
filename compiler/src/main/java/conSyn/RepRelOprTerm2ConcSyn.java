package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepRelOprTerm2AbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepRelOprTerm2ConcSyn extends AbstractConcSyn implements IConcSyn {
    private Term2ConcSyn term2ConcSyn;
    private RepRelOprTerm2ConcSyn repRelOprTerm2ConcSyn;

    public RepRelOprTerm2ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepRelOprTerm2AbsSyn toAbsSyn() throws ContextError {
        return new RepRelOprTerm2AbsSyn(term2ConcSyn.toAbsSyn(), repRelOprTerm2ConcSyn.toAbsSyn());
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
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.BOOLOPR) {

        } else if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.RELOPR) {
            consume();

            term2ConcSyn = new Term2ConcSyn(getTokenList(), getCounter());
            parseNext(term2ConcSyn);

            repRelOprTerm2ConcSyn = new RepRelOprTerm2ConcSyn(getTokenList(), getCounter())
            parseNext(repRelOprTerm2ConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
