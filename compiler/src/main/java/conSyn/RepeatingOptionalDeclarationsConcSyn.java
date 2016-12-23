package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();
            parseNext(new DeclarationConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalDeclarationsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
