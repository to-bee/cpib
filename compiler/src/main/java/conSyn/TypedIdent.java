package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class TypedIdent extends AbstractConcSyn implements IConcSyn {
    public TypedIdent(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.COLON) {
                consume();
                parseNext(new TypeDeclaration(getTokenList()));
            }
        } else {
            throwGrammarError();
        }
    }
}
