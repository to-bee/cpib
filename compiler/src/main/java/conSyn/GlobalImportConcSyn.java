package conSyn;

import absSyn.GlobalImportAbsSyn;
import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.token.IToken;

import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class GlobalImportConcSyn extends AbstractConcSyn implements IConcSyn {
    public GlobalImportConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public GlobalImportAbsSyn toAbsSyn()throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> OptionalFlowModeConcSyn = super.getListByType(OptionalFlowModeConcSyn.class);
        List<IAbsSyn> OptionalChangeModeConcSyn = super.getListByType(OptionalChangeModeConcSyn.class);

        return new GlobalImportAbsSyn(token, OptionalFlowModeConcSyn, OptionalChangeModeConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            parseNext(new OptionalFlowModeConcSyn(getTokenList(), getCounter()));
            parseNext(new OptionalChangeModeConcSyn(getTokenList(), getCounter()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();
            } else {
                throwGrammarError();
            }
        }
    }
}
