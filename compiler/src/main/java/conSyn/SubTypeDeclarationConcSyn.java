package conSyn;

import absSyn.SubTypeDeclarationAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by ylaub on 19.12.2016.
 */
public class SubTypeDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private IConcSyn subType;


    public SubTypeDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public SubTypeDeclarationAbsSyn toAbsSyn() throws ContextError {
        return new SubTypeDeclarationAbsSyn(subType.toAbsSyn());
    }

    /**
     * @throws GrammarError
     */
    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.TYPE) {
            subType = new SubTypeDeclarationConcSyn1(getTokenList(), getCounter());
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            subType = new SubTypeDeclarationConcSyn2(getTokenList(), getCounter());
        } else {
            throwGrammarError();
        }
        if (subType != null) {
            parseNext(subType);
        } else {
            throwGrammarError();
        }
    }
}
