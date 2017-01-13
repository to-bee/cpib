package conSyn;

import absSyn.OptionalMechModeAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalMechModeConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalMechModeConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }
    private Terminal terminal;

    @Override
    public OptionalMechModeAbsSyn toAbsSyn() throws ContextError {
        return new OptionalMechModeAbsSyn(terminal);
    }

    /**
     * TODO rewrite
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.IDENT
                || this.terminal.getType() == TerminalType.CHANGEMODE) {

        } else if (this.terminal.getType() == TerminalType.MECHMODE) {
            consume();
        } else{
            throwGrammarError();
        }
    }
}
