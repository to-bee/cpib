package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepeatingOptionalGlobalImportsAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalGlobalImportsConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalGlobalImportsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public RepeatingOptionalGlobalImportsAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> GlobalImportConcSyn = super.getListByType(GlobalImportConcSyn.class);
        List<IAbsSyn> RepeatingOptionalGlobalImportsConcSyn = super.getListByType(RepeatingOptionalGlobalImportsConcSyn.class);

        return new RepeatingOptionalGlobalImportsAbsSyn(token, GlobalImportConcSyn, RepeatingOptionalGlobalImportsConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LOCAL) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
            consume();
            parseNext(new GlobalImportConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalGlobalImportsConcSyn(getTokenList(), getCounter()));
        }
    }
}