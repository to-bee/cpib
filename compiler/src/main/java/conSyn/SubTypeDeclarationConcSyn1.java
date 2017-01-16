package conSyn;

import absSyn.SubTypeDeclarationAbsSyn1;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

/**
 * Created by ylaub on 19.12.2016.
 */
public class SubTypeDeclarationConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private IToken token;

    public SubTypeDeclarationConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public SubTypeDeclarationAbsSyn1 toAbsSyn() throws ContextError {
        return new SubTypeDeclarationAbsSyn1(token);
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        token = getTokenList().getCurrent();
        consume();
    }
}
