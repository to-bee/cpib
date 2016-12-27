package conSyn;

import absSyn.RepeatingOptionalProgramParametersAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalProgramParametersConcSyn extends AbstractConcSyn implements IConcSyn {
    private OptionalFlowModeConcSyn optionalFlowModeConcSyn;
    private OptionalChangeModeConcSyn optionalChangeModeConcSyn;
    private TypedIdentConcSyn typedIdentConcSyn;
    private RepeatingOptionalParametersConcSyn repeatingOptionalParametersConcSyn;

    public RepeatingOptionalProgramParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalProgramParametersAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalProgramParametersAbsSyn(optionalFlowModeConcSyn.toAbsSyn(), optionalChangeModeConcSyn.toAbsSyn(), typedIdentConcSyn.toAbsSyn(), repeatingOptionalParametersConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();

            optionalFlowModeConcSyn = new OptionalFlowModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalFlowModeConcSyn);

            optionalChangeModeConcSyn = new OptionalChangeModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalChangeModeConcSyn);

            typedIdentConcSyn = new TypedIdentConcSyn(getTokenList(), getCounter());
            parseNext(typedIdentConcSyn);

            repeatingOptionalParametersConcSyn = new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalParametersConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
