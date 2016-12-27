package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.TypeDeclarationAbsSyn;
import scanner.token.IToken;

import java.util.List;
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

    private IToken token;
    @Override
    public TypeDeclarationAbsSyn toAbsSyn()throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> SubTypeDeclarationConcSyn = super.getListByType(SubTypeDeclarationConcSyn.class);
        List<IAbsSyn> OptionalTypeDeclarationConcSyn = super.getListByType(OptionalTypeDeclarationConcSyn.class);

        return new TypeDeclarationAbsSyn(token, SubTypeDeclarationConcSyn, OptionalTypeDeclarationConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.TYPE
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            this.ident = (Ident) this.getTokenList().getCurrent();
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

                optionalTypeDeclarationConcSyn = new OptionalTypeDeclarationConcSyn(getTokenList(), getCounter())
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
