package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

import absSyn.OptionalGlobalDeclarationsAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalGlobalDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public OptionalGlobalDeclarationsAbsSyn toAbsSyn()throws ContextError {
        List<IAbsSyn> DeclarationsConcSyn = super.getListByType(DeclarationsConcSyn.class);
        return new OptionalGlobalDeclarationsAbsSyn(token, DeclarationsConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.GLOBAL) {
            consume();
            parseNext(new DeclarationsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
