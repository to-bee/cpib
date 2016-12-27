package conSyn;

import absSyn.CmdSkipAbsSyn;
import absSyn.FactorLiteralAbsSyn;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 17.12.16.
 */
public class FactorLiteralConcSyn extends AbstractConcSyn implements IConcSyn {
    public FactorLiteralConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public FactorLiteralAbsSyn toAbsSyn() throws ContextError {
        return new FactorLiteralAbsSyn();
    }

    @Override
    public void parse() throws GrammarError {
        consume();
    }
}
