package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.TypeDeclarationAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class TypeDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    public TypeDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn()throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> SubTypeDeclarationConcSyn = super.getListByType(SubTypeDeclarationConcSyn.class);
        List<IAbsSyn> OptionalTypeDeclarationConcSyn = super.getListByType(OptionalTypeDeclarationConcSyn.class);

        return new TypeDeclarationAbsSyn(token, SubTypeDeclarationConcSyn, OptionalTypeDeclarationConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.TYPE
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
        }
        else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();
            parseNext(new SubTypeDeclarationConcSyn(getTokenList(), getCounter()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
                consume();
                parseNext(new SubTypeDeclarationConcSyn(getTokenList(), getCounter()));
                parseNext(new OptionalTypeDeclarationConcSyn(getTokenList(), getCounter()));
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
