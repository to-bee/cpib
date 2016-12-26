package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;
import absSyn.DeclarationAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class DeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    public DeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> TODO = super.getListByType(TODO.class);
        IAbsSyn TODO2 = super.getOneByType(TODO2.class);

        return new Schnurzel(token, TODO, TODO2);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            parseNext(new StorageDeclarationConcSyn(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.FUN) {
            parseNext(new FunctionDeclarationConcSyn(getTokenList(), getCounter()));
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.PROC) {
            parseNext(new ProcedureDeclarationConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
