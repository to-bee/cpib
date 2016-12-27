package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.RepeatingOptionalCmdsAbsSyn;
import scanner.token.IToken;

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
        IAbsSyn CmdConcSyn = super.getOneByType(CmdConcSyn.class);
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
