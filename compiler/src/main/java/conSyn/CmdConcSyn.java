package conSyn;

import absSyn.CmdAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;
    private Terminal terminal;

    public CmdConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdAbsSyn toAbsSyn() throws ContextError {
        return new CmdAbsSyn(subType.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.SKIP) {
            subType = new CmdSkipConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.IF) {
            subType = new CmdIfConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.WHILE) {
            subType = new CmdWhileConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.CALL) {
            subType = new CmdCallConcSyn(getTokenList(), getCounter());
        } else if (this.terminal  == Terminal.IMAGINARY_PART
                || this.terminal  == Terminal.REAL
                || this.terminal  == Terminal.IMAG
                || this.terminal  == Terminal.LPAREN
                || this.terminal  == Terminal.ADDOPR
                || this.terminal  == Terminal.MINOPR
                || this.terminal  == Terminal.MULTOPR
                || this.terminal  == Terminal.DIVOPR
                || this.terminal  == Terminal.MODOPR
                || this.terminal  == Terminal.COMPLEMENT
                || this.terminal  == Terminal.IDENT
                || this.terminal  == Terminal.LITERAL) {
            subType = new CmdAssignConcSyn(getTokenList(), getCounter());
        } else if (this.terminal == Terminal.DEBUGIN
                || this.terminal == Terminal.DEBUGOUT) {
            consume();
            subType = new ExpressionConcSyn(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        }else {
            throwGrammarError();
        }
    }
}
