package conSyn;

import absSyn.TypeDeclarationTypeAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class TypeDeclarationTypeConcSyn extends AbstractConcSyn implements IConcSyn {
    private IToken type;

    public TypeDeclarationTypeConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public TypeDeclarationTypeAbsSyn toAbsSyn() throws ContextError {
        return new TypeDeclarationTypeAbsSyn(type);
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        type = (IToken) getTokenList().getCurrent();
        consume();

    }
}
