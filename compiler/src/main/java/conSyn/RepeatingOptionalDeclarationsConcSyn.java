package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepeatingOptionalDeclarationsAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalDeclarationsConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalDeclarationsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalDeclarationsAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> DeclarationConcSyn = super.getListByType(DeclarationConcSyn.class);
        List<IAbsSyn> RepeatingOptionalDeclarationsConcSyn = super.getListByType(RepeatingOptionalDeclarationsConcSyn.class);

        return new RepeatingOptionalDeclarationsAbsSyn(token, DeclarationConcSyn, RepeatingOptionalDeclarationsConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();
            parseNext(new DeclarationConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalDeclarationsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
