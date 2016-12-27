package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.IdentsAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class IdentsConcSyn extends AbstractConcSyn implements IConcSyn {
    private RepeatingOptionalIdentsConcSyn repeatingOptionalIdentsConcSyn;

    public IdentsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IdentsAbsSyn toAbsSyn() throws ContextError {
        return new IdentsAbsSyn(repeatingOptionalIdentsConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.SKIP) {
            consume();

            repeatingOptionalIdentsConcSyn = new RepeatingOptionalIdentsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalIdentsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
