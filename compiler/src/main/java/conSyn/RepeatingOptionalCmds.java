package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalCmds extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalCmds(ITokenList tokenList) {
        super(tokenList);
    }

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
            parseNext(new Cmd(getTokenList()));
            parseNext(new RepeatingOptionalCmds(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
