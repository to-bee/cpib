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
    private RepeatingOptionalProgramParametersConcSyn repeatingOptionalProgramParametersConcSyn;
    private OptionalFlowModeConcSyn optionalFlowModeConcSyn;
    private OptionalChangeModeConcSyn optionalChangeModeConcSyn;
    private TypedIdentConcSyn typedIdentConcSyn;

    public OptionalProgramParamConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalProgramParamAbsSyn toAbsSyn() throws ContextError {
        // TODO: maybe split up
        if (optionalFlowModeConcSyn != null) {
            return new OptionalProgramParamAbsSyn(optionalFlowModeConcSyn.toAbsSyn(), optionalChangeModeConcSyn.toAbsSyn(), typedIdentConcSyn.toAbsSyn(), repeatingOptionalProgramParametersConcSyn.toAbsSyn());
        } else {
            return null;
        }
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            // TODO: maybe split up
            optionalFlowModeConcSyn = new OptionalFlowModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalFlowModeConcSyn);

            optionalChangeModeConcSyn = new OptionalChangeModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalChangeModeConcSyn);

            typedIdentConcSyn = new TypedIdentConcSyn(getTokenList(), getCounter());
            parseNext(typedIdentConcSyn);

            repeatingOptionalProgramParametersConcSyn = new RepeatingOptionalProgramParametersConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalProgramParametersConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
