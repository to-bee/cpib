package conSyn;

import absSyn.RepeatingOptionalDeclarationsAbsSyn1;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalDeclarationsConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private DeclarationConcSyn declarationConcSyn;
    private RepeatingOptionalDeclarationsConcSyn repeatingOptionalDeclarationsConcSyn;

    public RepeatingOptionalDeclarationsConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalDeclarationsAbsSyn1 toAbsSyn() throws ContextError {
        return new RepeatingOptionalDeclarationsAbsSyn1(declarationConcSyn.toAbsSyn(), repeatingOptionalDeclarationsConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        declarationConcSyn = new DeclarationConcSyn(getTokenList(), getCounter());
        parseNext(declarationConcSyn);
        repeatingOptionalDeclarationsConcSyn = new RepeatingOptionalDeclarationsConcSyn(getTokenList(), getCounter());
        parseNext(repeatingOptionalDeclarationsConcSyn);
    }
}
