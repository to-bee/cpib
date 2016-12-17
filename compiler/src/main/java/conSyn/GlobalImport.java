package conSyn;

import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class GlobalImport extends AbstractConcSyn implements IConcSyn {
    public GlobalImport(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            parseNext(new OptionalFlowMode(getTokenList()));
            parseNext(new OptionalChangeMode(getTokenList()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();
            } else {
                throwGrammarError();
            }
        }
    }
}
