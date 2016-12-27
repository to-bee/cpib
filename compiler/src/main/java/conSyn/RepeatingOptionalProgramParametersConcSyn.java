package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepeatingOptionalProgramParametersAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalProgramParametersConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalProgramParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalProgramParametersAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> OptionalFlowModeConcSyn = super.getListByType(OptionalFlowModeConcSyn.class);
        List<IAbsSyn> OptionalChangeModeConcSyn = super.getListByType(OptionalChangeModeConcSyn.class);
        List<IAbsSyn> TypedIdentConcSyn = super.getListByType(TypedIdentConcSyn.class);
        List<IAbsSyn> RepeatingOptionalParametersConcSyn = super.getListByType(RepeatingOptionalParametersConcSyn.class);

        return new RepeatingOptionalProgramParametersAbsSyn(token, OptionalFlowModeConcSyn, OptionalChangeModeConcSyn, TypedIdentConcSyn, RepeatingOptionalParametersConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new OptionalFlowModeConcSyn(getTokenList(), getCounter()));;
            parseNext(new OptionalChangeModeConcSyn(getTokenList(), getCounter()));
            parseNext(new TypedIdentConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
