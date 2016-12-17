package conSyn;

import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalStorageDeclarations extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalStorageDeclarations(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();
            parseNext(new StorageDeclaration(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalStorageDeclarations(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
