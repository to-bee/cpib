package conSyn;

import absSyn.OptionalProgramParamAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalProgramParamConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private Terminal terminal;

    public OptionalProgramParamConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalProgramParamAbsSyn toAbsSyn() throws ContextError {
        return new OptionalProgramParamAbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.RPAREN) {
            subType = new EmptyConsumeConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.IDENT
                || this.terminal.getType() == TerminalType.CHANGEMODE
                || this.terminal.getType() == TerminalType.FLOWMODE) {
            subType = new OptionalProgramParamConcSyn1(getTokenList(), getCounter());
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
