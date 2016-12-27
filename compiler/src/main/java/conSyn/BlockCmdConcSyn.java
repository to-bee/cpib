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
    private CmdConcSyn cmdConcSyn;
    private RepeatingOptionalCmdsConcSyn repeatingOptionalCmdsConcSyn;

    public BlockCmdConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public BlockCmdAbsSyn toAbsSyn() throws ContextError {
        return new BlockCmdAbsSyn(cmdConcSyn.toAbsSyn(), repeatingOptionalCmdsConcSyn.toAbsSyn());
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
            cmdConcSyn = new CmdConcSyn(getTokenList(), getCounter());
            this.parseNext(cmdConcSyn);
            repeatingOptionalCmdsConcSyn = new RepeatingOptionalCmdsConcSyn(getTokenList(), getCounter());
            this.parseNext(repeatingOptionalCmdsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
