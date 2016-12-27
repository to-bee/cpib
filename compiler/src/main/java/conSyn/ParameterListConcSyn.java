package conSyn;

import absSyn.ParameterListAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class ParameterListConcSyn extends AbstractConcSyn implements IConcSyn {
    private OptionalParametersConcSyn optionalParametersConcSyn;

    public ParameterListConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ParameterListAbsSyn toAbsSyn()throws ContextError {
        return new ParameterListAbsSyn(optionalParametersConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();

            optionalParametersConcSyn = new OptionalParametersConcSyn(getTokenList(), getCounter());
            parseNext(optionalParametersConcSyn);

            if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
