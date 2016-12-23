package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class TypedIdentConcSyn extends AbstractConcSyn implements IConcSyn {
    public TypedIdentConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.COLON) {
                consume();
                parseNext(new TypeDeclarationConcSyn(getTokenList(), getCounter()));
            }
        } else {
            throwGrammarError();
        }
    }
}
