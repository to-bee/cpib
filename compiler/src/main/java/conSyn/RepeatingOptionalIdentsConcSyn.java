package conSyn;

import absSyn.RepeatingOptionalIdentsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalIdentsConcSyn extends AbstractConcSyn implements IConcSyn {
    private Ident ident;
    private IdentsConcSyn identsConcSyn;

    public RepeatingOptionalIdentsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalIdentsAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalIdentsAbsSyn(ident, identsConcSyn.toAbsSyn());
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
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDPROGRAM
                || getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                this.ident = (Ident) this.getTokenList().getCurrent();
                consume();

                identsConcSyn = new IdentsConcSyn(getTokenList(), getCounter());
                parseNext(identsConcSyn);
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
