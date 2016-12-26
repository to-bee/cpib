package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.SubTypeDeclarationAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by ylaub on 19.12.2016.
 */
public class SubTypeDeclarationConcSyn extends AbstractConcSyn implements IConcSyn{
    public SubTypeDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }
    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> SubTypeDeclarationConcSyn = super.getListByType(SubTypeDeclarationConcSyn.class);
        List<IAbsSyn> OptionalTypeDeclarationConcSyn = super.getListByType(OptionalTypeDeclarationConcSyn.class);

        return new SubTypeDeclarationAbsSyn(token, SubTypeDeclarationConcSyn, OptionalTypeDeclarationConcSyn);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.TYPE) {
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
