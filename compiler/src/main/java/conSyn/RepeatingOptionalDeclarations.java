package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalDeclarations extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalDeclarations(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();
            parseNext(new Declaration(getTokenList()));
            parseNext(new RepeatingOptionalDeclarations(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
