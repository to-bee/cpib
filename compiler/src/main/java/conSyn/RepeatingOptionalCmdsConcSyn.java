package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepeatingOptionalCmdsAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class RepeatingOptionalCmdsConcSyn extends AbstractConcSyn implements IConcSyn {
    public RepeatingOptionalCmdsConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //TODO: implement
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> TODO = super.getListByType(TODO.class);
        IAbsSyn TODO2 = super.getOneByType(TODO2.class);

        return new Schnurzel(token, TODO, TODO2);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.ENDPROC
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDFUN
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDWHILE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDIF
                || getTokenList().getCurrent().getTerminal() == Terminal.ELSE
                || getTokenList().getCurrent().getTerminal() == Terminal.ENDPROGRAM) {

        } else if (getTokenList().getCurrent().getTerminal() == Terminal.SEMICOLON) {
            consume();
            parseNext(new CmdConcSyn(getTokenList(), getCounter()));
            parseNext(new RepeatingOptionalCmdsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
