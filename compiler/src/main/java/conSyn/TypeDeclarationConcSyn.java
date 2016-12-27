package conSyn;

import absSyn.TypeDeclarationAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class TypeDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private Ident ident;
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn1;
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn2;
    private OptionalTypeDeclarationConcSyn optionalTypeDeclarationConcSyn;

    public TypeDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public TypeDeclarationAbsSyn toAbsSyn() throws ContextError {
        return new TypeDeclarationAbsSyn(
                ident,
                subTypeDeclarationConcSyn1.toAbsSyn(),
                subTypeDeclarationConcSyn2.toAbsSyn(),
                optionalTypeDeclarationConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.TYPE
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            if(getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                this.ident = (Ident) this.getTokenList().getCurrent();
            }
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
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
        } else {
            throwGrammarError();
        }
    }
}
