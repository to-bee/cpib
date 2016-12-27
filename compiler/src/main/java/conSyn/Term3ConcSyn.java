package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.Term3AbsSyn;
import scanner.token.IToken;

/**
 * Created by tobi on 17.12.16.
 */
public class Term3ConcSyn extends AbstractConcSyn implements IConcSyn {
    private FactorConcSyn FactorConcSyn;
    private RepMultOprFactorConcSyn RepMultOprFactorConcSyn;

    public Term3ConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        return new Term3AbsSyn(token, FactorConcSyn.toAbsSyn(), RepMultOprFactorConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IMAGINARY_PART
                || getTokenList().getCurrent().getTerminal() == Terminal.REAL
                || getTokenList().getCurrent().getTerminal() == Terminal.IMAG
                || getTokenList().getCurrent().getTerminal() == Terminal.LPAREN
                || getTokenList().getCurrent().getTerminal() == Terminal.ADDOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.MINOPR
                || getTokenList().getCurrent().getTerminal() == Terminal.NOT
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal() == Terminal.LITERAL) {
            FactorConcSyn = new FactorConcSyn(getTokenList(), getCounter());
            parseNext(FactorConcSyn);

            RepMultOprFactorConcSyn = new RepMultOprFactorConcSyn(getTokenList(), getCounter());
            parseNext(RepMultOprFactorConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
