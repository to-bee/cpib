package conSyn;

import absSyn.RepMultOprFactorAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepMultOprFactorConcSyn extends AbstractConcSyn implements IConcSyn {

    private IConcSyn subType;
    private Terminal token;

    public RepMultOprFactorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepMultOprFactorAbsSyn toAbsSyn() throws ContextError {
        return new RepMultOprFactorAbsSyn(subType.toAbsSyn(), token);
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.token = getTokenList().getCurrent().getTerminal();
        if (this.token == Terminal.RPAREN
                || this.token == Terminal.COMMA
                || this.token == Terminal.DO
                || this.token == Terminal.THEN
                || this.token == Terminal.ENDPROC
                || this.token == Terminal.ENDFUN
                || this.token == Terminal.ENDWHILE
                || this.token == Terminal.ENDIF
                || this.token == Terminal.ELSE
                || this.token == Terminal.ENDPROGRAM
                || this.token == Terminal.SEMICOLON
                || this.token == Terminal.BECOMES
                || this.token.getType() == TerminalType.BOOLOPR
                || this.token.getType() == TerminalType.RELOPR
                || this.token == Terminal.ADDOPR
                || this.token == Terminal.MINOPR) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (this.token == Terminal.MULTOPR
                || this.token == Terminal.DIVOPR
                || this.token == Terminal.MODOPR) {
            subType = new RepMultOprFactorConcSyn1(getTokenList(), getCounter());
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
