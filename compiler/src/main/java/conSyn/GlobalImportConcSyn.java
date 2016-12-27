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

    public GlobalImportConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public GlobalImportAbsSyn toAbsSyn() throws ContextError {
        return new GlobalImportAbsSyn(ident, optionalFlowModeConcSyn.toAbsSyn(), optionalChangeModeConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {

            optionalFlowModeConcSyn = new OptionalFlowModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalFlowModeConcSyn);

            optionalChangeModeConcSyn = new OptionalChangeModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalChangeModeConcSyn);
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                this.ident = (Ident) getTokenList().getCurrent();
                consume();
            } else {
                throwGrammarError();
            }
        }
    }
}
