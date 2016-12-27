package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.ParameterAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class ParameterConcSyn extends AbstractConcSyn implements IConcSyn {
    public ParameterConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ParameterAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> OptionalFlowModeConcSyn = super.getListByType(OptionalFlowModeConcSyn.class);
        List<IAbsSyn> OptionalMechModeConcSyn = super.getListByType(OptionalMechModeConcSyn.class);
        List<IAbsSyn> StorageDeclarationConcSyn = super.getListByType(StorageDeclarationConcSyn.class);

        return new ParameterAbsSyn(token, OptionalFlowModeConcSyn, OptionalMechModeConcSyn, StorageDeclarationConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.MECHMODE
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.FLOWMODE) {
            parseNext(new OptionalFlowModeConcSyn(getTokenList(), getCounter()));;
            parseNext(new OptionalMechModeConcSyn(getTokenList(), getCounter()));;
            parseNext(new StorageDeclarationConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
