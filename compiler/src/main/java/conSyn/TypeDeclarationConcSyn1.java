package conSyn;

import absSyn.TypeDeclarationAbsSyn1;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class TypeDeclarationConcSyn1 extends AbstractConcSyn implements IConcSyn {
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn1;
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn2;
    private OptionalTypeDeclarationConcSyn optionalTypeDeclarationConcSyn;

    public TypeDeclarationConcSyn1(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public TypeDeclarationAbsSyn1 toAbsSyn() throws ContextError {
        return new TypeDeclarationAbsSyn1(
                subTypeDeclarationConcSyn1.toAbsSyn(),
                subTypeDeclarationConcSyn2.toAbsSyn(),
                optionalTypeDeclarationConcSyn.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        consume();
        subTypeDeclarationConcSyn1 = new SubTypeDeclarationConcSyn(getTokenList(), getCounter());
        parseNext(subTypeDeclarationConcSyn1);
        if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();

            subTypeDeclarationConcSyn2 = new SubTypeDeclarationConcSyn(getTokenList(), getCounter());
            parseNext(subTypeDeclarationConcSyn2);

            optionalTypeDeclarationConcSyn = new OptionalTypeDeclarationConcSyn(getTokenList(), getCounter());
            parseNext(optionalTypeDeclarationConcSyn);
            if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                consume();
            } else {
                throwGrammarError();
            }
        } else {
            //TODO ContextError here, Tuple with only 1 Element
            throwGrammarError();
        }
    }

}
