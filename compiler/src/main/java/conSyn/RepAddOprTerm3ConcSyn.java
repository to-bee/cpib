package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.RepAddOprTerm3AbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepAddOprTerm3ConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private Terminal terminal;

    public RepAddOprTerm3ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepAddOprTerm3AbsSyn toAbsSyn() throws ContextError {
        return new RepAddOprTerm3AbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.ADDOPR
                || this.terminal == Terminal.MINOPR
                || this.terminal == Terminal.MULTOPR
                || this.terminal == Terminal.DIVOPR
                || this.terminal == Terminal.MODOPR) {
            subType = new RepAddOprTerm3ConcSyn1(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.RPAREN
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
                || this.terminal.getType() == TerminalType.BOOLOPR
                || this.terminal.getType() == TerminalType.RELOPR) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        }else {
            throwGrammarError();
        }
    }
}
