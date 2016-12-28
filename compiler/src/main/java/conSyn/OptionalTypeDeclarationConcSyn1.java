package conSyn;

import absSyn.OptionalTypeDeclarationAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by ylaub on 19.12.2016.
 */
public class OptionalTypeDeclarationConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn;

    public OptionalTypeDeclarationConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalTypeDeclarationAbsSyn1 toAbsSyn() throws ContextError {
        return new OptionalTypeDeclarationAbsSyn1(subTypeDeclarationConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        subTypeDeclarationConcSyn = new SubTypeDeclarationConcSyn(getTokenList(), getCounter());
        parseNext(subTypeDeclarationConcSyn);
    }
}
