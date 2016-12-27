package conSyn;

import absSyn.ParameterAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class ParameterConcSyn extends AbstractConcSyn implements IConcSyn {
    private StorageDeclarationConcSyn storageDeclarationConcSyn;
    private OptionalFlowModeConcSyn optionalFlowModeConcSyn;
    private OptionalMechModeConcSyn optionalMechModeConcSyn;

    public ParameterConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ParameterAbsSyn toAbsSyn() throws ContextError {
        return new ParameterAbsSyn(optionalFlowModeConcSyn.toAbsSyn(), optionalMechModeConcSyn.toAbsSyn(), storageDeclarationConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.MECHMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {

            optionalFlowModeConcSyn = new OptionalFlowModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalFlowModeConcSyn);

            optionalMechModeConcSyn = new OptionalMechModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalMechModeConcSyn);

            storageDeclarationConcSyn = new StorageDeclarationConcSyn(getTokenList(), getCounter());
            parseNext(storageDeclarationConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
