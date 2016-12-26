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
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> CmdConcSyn = super.getListByType(CmdConcSyn.class);
        List<IAbsSyn> RepeatingOptionalCmdsConcSyn = super.getListByType(RepeatingOptionalCmdsConcSyn.class);

        return new RepeatingOptionalCmdsAbsSyn(token, CmdConcSyn, RepeatingOptionalCmdsConcSyn);
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
