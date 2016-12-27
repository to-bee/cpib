package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.ExpressionListAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class ExpressionListConcSyn extends AbstractConcSyn implements IConcSyn {
    private OptionalExpressionsConcSyn optionalExpressionsConcSyn;

    public ExpressionListConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ExpressionListAbsSyn toAbsSyn() throws ContextError {
        return new ExpressionListAbsSyn(optionalExpressionsConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();
            optionalExpressionsConcSyn = new OptionalExpressionsConcSyn(getTokenList(), getCounter());
            parseNext(optionalExpressionsConcSyn);
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
