package conSyn;

import absSyn.RepeatingOptionalIdentsAbsSyn1;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalIdentsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private Ident ident;
    private IdentsConcSyn identsConcSyn;

    public RepeatingOptionalIdentsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalIdentsAbsSyn1 toAbsSyn() throws ContextError {
        return new RepeatingOptionalIdentsAbsSyn1(ident, identsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            this.ident = (Ident) this.getTokenList().getCurrent();
            consume();
            identsConcSyn = new IdentsConcSyn(getTokenList(), getCounter());
            parseNext(identsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}