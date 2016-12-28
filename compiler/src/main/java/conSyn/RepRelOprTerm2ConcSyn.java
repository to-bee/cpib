package conSyn;

import absSyn.RepRelOprTerm2AbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepRelOprTerm2ConcSyn extends AbstractConcSyn implements IConcSyn {
    private Term2ConcSyn term2ConcSyn;
    private RepRelOprTerm2ConcSyn repRelOprTerm2ConcSyn;
    private IConcSyn subType;

    public RepRelOprTerm2ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepRelOprTerm2AbsSyn toAbsSyn() throws ContextError {
        return new RepRelOprTerm2AbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
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
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.RELOPR) {
            subType = new RepRelOprTerm2ConcSyn1(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        } else {
            throwGrammarError();
        }
    }
}
