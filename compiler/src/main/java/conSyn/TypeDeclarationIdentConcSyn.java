package conSyn;

import absSyn.TypeDeclarationIdentAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class TypeDeclarationIdentConcSyn extends AbstractConcSyn implements IConcSyn {
    private Ident ident;

    public TypeDeclarationIdentConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public TypeDeclarationIdentAbsSyn toAbsSyn() throws ContextError {
        return new TypeDeclarationIdentAbsSyn(ident);
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        ident = (Ident) getTokenList().getCurrent();
        consume();
    }
}
