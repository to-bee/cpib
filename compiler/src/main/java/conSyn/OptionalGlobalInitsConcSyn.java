package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.OptionalGlobalInitsAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalInitsConcSyn extends AbstractConcSyn implements IConcSyn {
    private IdentsConcSyn identsConcSyn;

    public OptionalGlobalInitsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public OptionalGlobalInitsAbsSyn toAbsSyn() throws ContextError {
        return new OptionalGlobalInitsAbsSyn(identsConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.ENDPROC
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDFUN
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDWHILE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDIF
                || getTokenList().getCurrent().getTerminal() == Terminal.ELSE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDPROGRAM
                || getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.INIT) {
            consume();

            identsConcSyn = new IdentsConcSyn(getTokenList(), getCounter());
            parseNext(identsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
