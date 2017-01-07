package conSyn;

import absSyn.CmdAbsSyn;
import absSyn.IAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

import java.util.List;

/**
 * Created by tobi on 17.12.16.
 */
public class CmdConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;

    public CmdConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public CmdAbsSyn toAbsSyn() throws ContextError {
        return new CmdAbsSyn(subType.toAbsSyn());
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.SKIP) {
            subType = new CmdSkipConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IF) {
            subType = new CmdIfConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.WHILE) {
            subType = new CmdWhileConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.CALL) {
            subType = new CmdCallConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
                || getTokenList().getCurrent().getTerminal() == Terminal.REAL
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAG
                || getTokenList().getCurrent().getTerminal() == Terminal.LPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MULTOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.DIVOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MODOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {
            subType = new CmdAssignConcSyn(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.DEBUGIN
                || getTokenList().getCurrent().getTerminal() == Terminal.DEBUGOUT) {
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
