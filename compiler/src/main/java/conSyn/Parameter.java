package conSyn;

import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class Parameter extends AbstractConcSyn implements IConcSyn {
    public Parameter(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.MECHMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            parseNext(new OptionalFlowMode(getTokenList()));;
            parseNext(new OptionalMechMode(getTokenList()));;
            parseNext(new StorageDeclaration(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
