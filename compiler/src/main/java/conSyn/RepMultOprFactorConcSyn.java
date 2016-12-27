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
    private FactorConcSyn factorConcSyn;
    private RepMultOprFactorConcSyn repMultOprFactorConcSyn;

    public RepMultOprFactorConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepMultOprFactorAbsSyn toAbsSyn() throws ContextError {
        return new RepMultOprFactorAbsSyn(factorConcSyn.toAbsSyn(), repMultOprFactorConcSyn.toAbsSyn());
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
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.MULTOPR) {
            consume();
            factorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
            parseNext(factorConcSyn);

            repMultOprFactorConcSyn = new RepMultOprFactorConcSyn(getTokenList(), getCounter());
            parseNext(repMultOprFactorConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
