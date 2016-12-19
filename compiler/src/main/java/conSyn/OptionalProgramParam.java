package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalProgramParam extends AbstractConcSyn implements IConcSyn {
    public OptionalProgramParam(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            parseNext(new OptionalFlowMode(getTokenList(), getCounter()));
            parseNext(new OptionalChangeMode(getTokenList(), getCounter()));
            parseNext(new TypedIdent(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalProgramParameters(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
