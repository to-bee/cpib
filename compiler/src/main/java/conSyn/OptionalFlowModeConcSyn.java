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
    private Terminal terminal;

    @Override
    public OptionalFlowModeAbsSyn toAbsSyn() throws ContextError {
        return new OptionalFlowModeAbsSyn(terminal);
    }

    /**
     * TODO Rewrite (no ident)
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.IDENT
                || this.terminal.getType() == TerminalType.MECHMODE
                || this.terminal.getType() == TerminalType.CHANGEMODE) {

        } else if (this.terminal.getType() == TerminalType.FLOWMODE) {
            consume();
        } else {
            throwGrammarError();
        }
    }
}
