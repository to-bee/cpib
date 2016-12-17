package conSyn;

import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class StorageDeclaration extends AbstractConcSyn implements IConcSyn {
    public StorageDeclaration(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            parseNext(new OptionalChangeMode(getTokenList(), getCounter()));
            parseNext(new TypedIdent(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
