package conSyn;

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
    private ParameterConcSyn parameterConcSyn;
    private RepeatingOptionalParametersConcSyn repeatingOptionalParametersConcSyn;

    public OptionalParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalParametersAbsSyn toAbsSyn() throws ContextError {
        return new OptionalParametersAbsSyn(parameterConcSyn.toAbsSyn(), repeatingOptionalParametersConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.MECHMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {

            parameterConcSyn = new ParameterConcSyn(getTokenList(), getCounter());
            parseNext(parameterConcSyn);

            repeatingOptionalParametersConcSyn = new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalParametersConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
