package conSyn;

import absSyn.TypedIdentAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class TypedIdentConcSyn extends AbstractConcSyn implements IConcSyn {
    private Ident ident;
    private TypeDeclarationConcSyn typeDeclarationConcSyn;

    public TypedIdentConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public TypedIdentAbsSyn toAbsSyn() throws ContextError {
        return new TypedIdentAbsSyn(ident, typeDeclarationConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            this.ident = (Ident) getTokenList().getCurrent();
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.COLON) {
                consume();
                typeDeclarationConcSyn = new TypeDeclarationConcSyn(getTokenList(), getCounter());
                parseNext(typeDeclarationConcSyn);
            } else {
                //Maybe change: added exception 27.12.2016, by Yves
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }
    }
}
