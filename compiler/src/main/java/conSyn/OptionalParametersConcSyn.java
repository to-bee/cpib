package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.OptionalParametersAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalParametersConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalParametersConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //TODO: implement
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> ParameterConcSyn = super.getListByType(ParameterConcSyn.class);
        List<IAbsSyn> RepeatingOptionalParametersConcSyn = super.getListByType(RepeatingOptionalParametersConcSyn.class);

        return new OptionalParametersAbsSyn(token, ParameterConcSyn, RepeatingOptionalParametersConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.MECHMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            parseNext(new ParameterConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalParametersConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
