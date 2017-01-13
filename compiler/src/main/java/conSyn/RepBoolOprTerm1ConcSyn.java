package conSyn;

import absSyn.RepBoolOprTerm1AbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepBoolOprTerm1ConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private Terminal terminal;

    public RepBoolOprTerm1ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepBoolOprTerm1AbsSyn toAbsSyn() throws ContextError {
        return new RepBoolOprTerm1AbsSyn(subType.toAbsSyn(), terminal);
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
                || this.terminal == Terminal.BECOMES) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (this.terminal.getType() == TerminalType.BOOLOPR) {
            subType = new RepBoolOprTerm1ConcSyn1(getTokenList(), getCounter());
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
