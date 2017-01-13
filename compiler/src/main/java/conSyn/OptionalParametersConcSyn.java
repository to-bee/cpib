package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.OptionalParametersAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalParametersConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private Terminal terminal;

    public OptionalParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalParametersAbsSyn toAbsSyn() throws ContextError {
        return new OptionalParametersAbsSyn(subType.toAbsSyn(), terminal);
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.RPAREN) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.IDENT) {
            subType = new OptionalParametersConcSyn1(getTokenList(), getCounter());
        } else if (this.terminal.getType() == TerminalType.CHANGEMODE
                || this.terminal.getType() == TerminalType.MECHMODE
                || this.terminal.getType() == TerminalType.FLOWMODE) {
            subType = new OptionalParametersConcSyn2(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        } else {
            throwGrammarError();
        }
    }
}
