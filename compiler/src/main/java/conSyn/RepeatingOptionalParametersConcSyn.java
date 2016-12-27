package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepeatingOptionalParametersAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalParametersConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> ParameterConcSyn = super.getListByType(ParameterConcSyn.class);
        List<IAbsSyn> RepeatingOptionalParametersConcSyn = super.getListByType(RepeatingOptionalParametersConcSyn.class);

        return new RepeatingOptionalParametersAbsSyn(token, ParameterConcSyn, RepeatingOptionalParametersConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new ParameterConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
