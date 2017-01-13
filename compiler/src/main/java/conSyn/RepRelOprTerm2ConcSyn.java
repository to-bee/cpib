package conSyn;

import absSyn.RepRelOprTerm2AbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepRelOprTerm2ConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private Terminal terminal;

    public RepRelOprTerm2ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepRelOprTerm2AbsSyn toAbsSyn() throws ContextError {
        return new RepRelOprTerm2AbsSyn(subType.toAbsSyn(), terminal);
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.RPAREN
                || this.terminal == Terminal.COMMA
                || this.terminal == Terminal.DO
                || this.terminal == Terminal.THEN
                || this.terminal == Terminal.ENDPROC
                || this.terminal == Terminal.ENDFUN
                || this.terminal == Terminal.ENDWHILE
                || this.terminal == Terminal.ENDIF
                || this.terminal == Terminal.ELSE
                || this.terminal == Terminal.ENDPROGRAM
                || this.terminal == Terminal.SEMICOLON
                || this.terminal == Terminal.BECOMES
                || this.terminal.getType() == TerminalType.BOOLOPR) {
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
