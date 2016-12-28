package conSyn;

import absSyn.OptionalTypeDeclarationAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by ylaub on 19.12.2016.
 */
public class OptionalTypeDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;

    public OptionalTypeDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public OptionalTypeDeclarationAbsSyn toAbsSyn() throws ContextError {
        return new OptionalTypeDeclarationAbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            subType = new EmptyConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            subType = new OptionalTypeDeclarationConcSyn1(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        } else {
            throwGrammarError();
        }
    }
}
