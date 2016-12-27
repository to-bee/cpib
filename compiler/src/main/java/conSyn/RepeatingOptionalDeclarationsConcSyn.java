package conSyn;

import absSyn.RepeatingOptionalDeclarationsAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    private DeclarationConcSyn declarationConcSyn;
    private RepeatingOptionalDeclarationsConcSyn repeatingOptionalDeclarationsConcSyn;

    public RepeatingOptionalDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalDeclarationsAbsSyn toAbsSyn() throws ContextError {
        return new RepeatingOptionalDeclarationsAbsSyn(declarationConcSyn.toAbsSyn(), repeatingOptionalDeclarationsConcSyn.toAbsSyn());
    }

    /**
     * TODO
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();

            declarationConcSyn = new DeclarationConcSyn(getTokenList(), getCounter());
            parseNext(declarationConcSyn);

            repeatingOptionalDeclarationsConcSyn = new RepeatingOptionalDeclarationsConcSyn(getTokenList(), getCounter());
            parseNext(repeatingOptionalDeclarationsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
