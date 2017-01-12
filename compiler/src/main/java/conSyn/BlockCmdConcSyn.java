package conSyn;

import absSyn.BlockCmdAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class BlockCmdConcSyn extends AbstractConcSyn implements IConcSyn {
    private CmdConcSyn cmdConcSyn;
    private RepeatingOptionalCmdsConcSyn repeatingOptionalCmdsConcSyn;
    private Terminal terminal;

    public BlockCmdConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public BlockCmdAbsSyn toAbsSyn() throws ContextError {
        return new BlockCmdAbsSyn(cmdConcSyn.toAbsSyn(), repeatingOptionalCmdsConcSyn.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.DEBUGOUT
                || this.terminal == Terminal.DEBUGIN
                || this.terminal == Terminal.CALL
                || this.terminal == Terminal.WHILE
                || this.terminal == Terminal.IF
                || this.terminal == Terminal.IMAGINARY_PART
                || this.terminal == Terminal.REAL
                || this.terminal == Terminal.IMAG
                || this.terminal == Terminal.LPAREN
                || this.terminal == Terminal.ADDOPR
                || this.terminal == Terminal.MINOPR
                || this.terminal == Terminal.MULTOPR
                || this.terminal == Terminal.DIVOPR
                || this.terminal == Terminal.MODOPR
                || this.terminal == Terminal.COMPLEMENT
                || this.terminal == Terminal.IDENT
                || this.terminal == Terminal.LITERAL
                || this.terminal == Terminal.SKIP) {
            cmdConcSyn = new CmdConcSyn(getTokenList(), getCounter());
            this.parseNext(cmdConcSyn);

            repeatingOptionalCmdsConcSyn = new RepeatingOptionalCmdsConcSyn(getTokenList(), getCounter());
            this.parseNext(repeatingOptionalCmdsConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
