package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.StorageDeclarationAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class StorageDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    public StorageDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn()throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> OptionalChangeModeConcSyn = super.getListByType(OptionalChangeModeConcSyn.class);
        List<IAbsSyn> TypedIdentConcSyn = super.getListByType(TypedIdentConcSyn.class);

        return new StorageDeclarationAbsSyn(token, OptionalChangeModeConcSyn, TypedIdentConcSyn);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            parseNext(new OptionalChangeModeConcSyn(getTokenList(), getCounter()));
            parseNext(new TypedIdentConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
