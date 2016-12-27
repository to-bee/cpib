package conSyn;

import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.SubTypeDeclarationAbsSyn;

/**
 * Created by ylaub on 19.12.2016.
 */
public class SubTypeDeclarationConcSyn extends AbstractConcSyn implements IConcSyn{
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn1;
    private SubTypeDeclarationConcSyn subTypeDeclarationConcSyn2;
    private OptionalTypeDeclarationConcSyn optionalTypeDeclarationConcSyn;
    private Terminal type;

    public SubTypeDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }
    @Override
    public SubTypeDeclarationAbsSyn toAbsSyn() throws ContextError {
        return new SubTypeDeclarationAbsSyn(
                type,
                subTypeDeclarationConcSyn1.toAbsSyn(),
                subTypeDeclarationConcSyn2.toAbsSyn(),
                optionalTypeDeclarationConcSyn.toAbsSyn());
    }
    /**
     * TODO
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.TYPE) {
            type = getTokenList().getCurrent().getTerminal();
            consume();
        }
        else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
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
                }else{
                    throwGrammarError();
                }
            } else{
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }

    }
}
