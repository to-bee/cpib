package conSyn;

import absSyn.SubTypeDeclarationAbsSyn;
import absSyn.SubTypeDeclarationAbsSyn2;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by ylaub on 19.12.2016.
 */
public class SubTypeDeclarationConcSyn2 extends AbstractConcSyn implements IConcSyn {
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn1;
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn2;
    private OptionalTypeDeclarationConcSyn optionalTypeDeclarationConcSyn;

    public SubTypeDeclarationConcSyn2(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public SubTypeDeclarationAbsSyn2 toAbsSyn() throws ContextError {
        return new SubTypeDeclarationAbsSyn2(
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
            throwGrammarError();
        }
    }
}
