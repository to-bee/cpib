package conSyn;

import absSyn.ExpressionAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class ExpressionConcSyn extends AbstractConcSyn implements IConcSyn {
    private Term1ConcSyn term1ConcSyn;
    private RepBoolOprTerm1ConcSyn repBoolOprTerm1ConcSyn;
    private IToken token;
    private Terminal terminal;

    public ExpressionConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public ExpressionAbsSyn toAbsSyn() throws ContextError {
        return new ExpressionAbsSyn(token, term1ConcSyn.toAbsSyn(),repBoolOprTerm1ConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        this.terminal = getTokenList().getCurrent().getTerminal();
        if (this.terminal == Terminal.IMAGINARY_PART
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
                || this.terminal == Terminal.LITERAL) {
            token = getTokenList().getCurrent();
            term1ConcSyn = new Term1ConcSyn(getTokenList(), getCounter());
            parseNext(term1ConcSyn);
            repBoolOprTerm1ConcSyn = new RepBoolOprTerm1ConcSyn(getTokenList(), getCounter());
            parseNext(repBoolOprTerm1ConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
