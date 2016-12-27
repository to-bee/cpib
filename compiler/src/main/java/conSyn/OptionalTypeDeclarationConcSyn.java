package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.OptionalTypeDeclarationAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by ylaub on 19.12.2016.
 */
public class OptionalTypeDeclarationConcSyn extends AbstractConcSyn implements IConcSyn{
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn;

    public OptionalTypeDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalTypeDeclarationAbsSyn toAbsSyn()throws ContextError {
        return new OptionalTypeDeclarationAbsSyn(subTypeDeclarationConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        }
        else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA){
            consume();

            subTypeDeclarationConcSyn = new SubTypeDeclarationConcSyn(getTokenList(), getCounter())
            parseNext(subTypeDeclarationConcSyn);
        }else{
            throwGrammarError();
        }
    }
}
