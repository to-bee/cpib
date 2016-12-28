package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.OptionalIdentAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalIdentConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;

    public OptionalIdentConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalIdentAbsSyn toAbsSyn()throws ContextError {
        return new OptionalIdentAbsSyn(subType.toAbsSyn());

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
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.BOOLOPR
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.RELOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MULTOPR) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.INIT) {
            subType = new EmptyConsumeConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            subType = new OptionalIdentConcSyn1(getTokenList(), getCounter());
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
