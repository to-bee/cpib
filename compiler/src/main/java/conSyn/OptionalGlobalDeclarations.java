package conSyn;

import scanner.datatypes.Terminal;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalDeclarations extends AbstractConcSyn implements IConcSyn {
    public OptionalGlobalDeclarations(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.GLOBAL) {
            consume();
            parseNext(new Declarations(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
