package conSyn;

import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalProgramParam extends AbstractConcSyn implements IConcSyn {
    public OptionalProgramParam(ITokenList tokenList) {
        super(tokenList);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            parseNext(new OptionalFlowMode(getTokenList()));
            parseNext(new OptionalChangeMode(getTokenList()));
            parseNext(new TypedIdent(getTokenList()));
            parseNext(new RepeatingOptionalProgramParameters(getTokenList()));
        } else {
            throwGrammarError();
        }
    }
}
