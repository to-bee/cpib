package conSyn;

import absSyn.IAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

/**
 * Created by ylaub on 19.12.2016.
 */
public class subTypeDeclaration  extends AbstractConcSyn implements IConcSyn{
    public subTypeDeclaration(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal().getType() == TerminalType.TYPE
                || getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
            consume();
        }
        else if (getTokenList().getCurrent().getTerminal() == Terminal.LPAREN) {
            consume();
            parseNext(new subTypeDeclaration(getTokenList(), getCounter()));
            if (getTokenList().getCurrent().getTerminal() == Terminal.COMMA) {
                consume();
                parseNext(new subTypeDeclaration(getTokenList(), getCounter()));
                parseNext(new optionalTypeDeclaration(getTokenList(), getCounter()));
                if (getTokenList().getCurrent().getTerminal() == Terminal.RPAREN) {
                    consume();
                }else{
                    throwGrammarError();
                }
            } else{
                throwGrammarError();
            }
        } else {
            throwGrammarError();
        }

    }

    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        return null;
    }
}
