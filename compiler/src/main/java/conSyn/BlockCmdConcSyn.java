package conSyn;

import absSyn.BlockCmdAbsSyn;
import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import absSyn.commands.Cmd;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class BlockCmdConcSyn extends AbstractConcSyn implements IConcSyn {
    private IToken token;
    public BlockCmdConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    /**
     * Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
     * @return
     * @throws ContextError
     */
    @Override
    public BlockCmdAbsSyn toAbsSyn() throws ContextError {
        Cmd Cmd = new Cmd(super.getOneByType(CmdConcSyn.class));
        List<IAbsSyn> RepeatingOptionalCmds = super.getListByType(RepeatingOptionalCmdsConcSyn.class);

        return new BlockCmdAbsSyn(token, Cmd, RepeatingOptionalCmds);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.DEBUGOUT
                || getTokenList().getCurrent().getTerminal() == Terminal.DEBUGIN
                || getTokenList().getCurrent().getTerminal() == Terminal.CALL
                || getTokenList().getCurrent().getTerminal() == Terminal.WHILE
                || getTokenList().getCurrent().getTerminal() == Terminal.IF
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
                || getTokenList().getCurrent().getTerminal() == Terminal.REAL
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAG
                || getTokenList().getCurrent().getTerminal() == Terminal.LPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL
                || getTokenList().getCurrent().getTerminal() == Terminal.SKIP) {
            this.parseNext(new CmdConcSyn(getTokenList(), getCounter()));
            this.parseNext(new RepeatingOptionalCmdsConcSyn(getTokenList(), getCounter()));
        } else {
            throwGrammarError();
        }
    }
}
