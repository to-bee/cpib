package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by ylaub on 19.12.2016.
 */
public class OptionalTypeDeclarationConcSyn extends AbstractConcSyn implements IConcSyn{
    public OptionalTypeDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        }
        else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA){
            consume();
            parseNext(new SubTypeDeclarationConcSyn(getTokenList(), getCounter()));
        }else{
            throwGrammarError();
        }
    }

    @Override
    public IAbsSyn toAbsSyn() {
        return null;
    }
}
