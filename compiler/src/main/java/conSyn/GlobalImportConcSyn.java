package conSyn;

import absSyn.GlobalImportAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class GlobalImportConcSyn extends AbstractConcSyn implements IConcSyn {
    private IToken token;
    private OptionalFlowModeConcSyn optionalFlowModeConcSyn;
    private OptionalChangeModeConcSyn optionalChangeModeConcSyn;
    private Ident ident;
    private Terminal terminal;

    public GlobalImportConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public GlobalImportAbsSyn toAbsSyn() throws ContextError {
        return new GlobalImportAbsSyn(ident, optionalFlowModeConcSyn.toAbsSyn(), optionalChangeModeConcSyn.toAbsSyn(), terminal);
    }


    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.IDENT
                || this.terminal.getType() == TerminalType.CHANGEMODE
                || this.terminal.getType() == TerminalType.FLOWMODE) {

            optionalFlowModeConcSyn = new OptionalFlowModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalFlowModeConcSyn);

            optionalChangeModeConcSyn = new OptionalChangeModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalChangeModeConcSyn);
            if (this.terminal == Terminal.IDENT) {
                this.ident = (Ident) getTokenList().getCurrent();
                consume();
            } else {
                throwGrammarError();
            }
        }
    }
}
