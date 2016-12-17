package conSyn;

import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class TypeDeclaration extends AbstractConcSyn implements IConcSyn {
    public TypeDeclaration(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.TYPE
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
        } else {
            throwGrammarError();
        }
    }
}
