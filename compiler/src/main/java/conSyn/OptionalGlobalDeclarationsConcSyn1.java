package conSyn;

import absSyn.OptionalGlobalDeclarationsAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class OptionalGlobalDeclarationsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private DeclarationsConcSyn declarationsConcSyn;

    public OptionalGlobalDeclarationsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalGlobalDeclarationsAbsSyn1 toAbsSyn() throws ContextError {
        return new OptionalGlobalDeclarationsAbsSyn1(declarationsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        declarationsConcSyn = new DeclarationsConcSyn(getTokenList(), getCounter());
        parseNext(declarationsConcSyn);
    }
}
