package conSyn;

import absSyn.OptionalFlowModeAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalFlowModeConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalFlowModeConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalFlowModeAbsSyn toAbsSyn() throws ContextError {
        return new OptionalFlowModeAbsSyn();
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.MECHMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {

        } else if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            consume();
        } else {
            throwGrammarError();
        }
    }
}
