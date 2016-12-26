package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

import absSyn.OptionalProgramParamAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class OptionalProgramParamConcSyn extends AbstractConcSyn implements IConcSyn {
    public OptionalProgramParamConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn()throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> OptionalFlowModeConcSyn = super.getListByType(OptionalFlowModeConcSyn.class);
        List<IAbsSyn> OptionalChangeModeConcSyn = super.getListByType(OptionalChangeModeConcSyn.class);
        List<IAbsSyn> TypedIdentConcSyn = super.getListByType(TypedIdentConcSyn.class);
        List<IAbsSyn> RepeatingOptionalProgramParametersConcSyn = super.getListByType(RepeatingOptionalProgramParametersConcSyn.class);

        return new OptionalProgramParamAbsSyn(token, OptionalFlowModeConcSyn, OptionalChangeModeConcSyn, TypedIdentConcSyn, RepeatingOptionalProgramParametersConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
            consume();
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            parseNext(new OptionalFlowModeConcSyn(getTokenList(), getCounter()));
            parseNext(new OptionalChangeModeConcSyn(getTokenList(), getCounter()));
            parseNext(new TypedIdentConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalProgramParametersConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
