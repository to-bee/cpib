package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepeatingOptionalCmdsAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalCmdsConcSyn extends AbstractConcSyn implements IConcSyn {
    private RepeatingOptionalCmdsConcSyn repeatingOptionalCmdsConcSyn;
    private CmdConcSyn cmdConcSyn;

    public RepeatingOptionalCmdsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalCmdsAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalCmdsAbsSyn(cmdConcSyn.toAbsSyn(), repeatingOptionalCmdsConcSyn.toAbsSyn());
    }

    /**
     * TODO
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.ENDPROC
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDFUN
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDWHILE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDIF
                || getTokenList().getCurrent().getTerminal() == Terminal.ELSE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDPROGRAM) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();

            cmdConcSyn = new CmdConcSyn(getTokenList(), getCounter());
            parseNext(cmdConcSyn);

            repeatingOptionalCmdsConcSyn = new RepeatingOptionalCmdsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalCmdsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
