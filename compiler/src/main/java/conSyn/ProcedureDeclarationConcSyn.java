package conSyn;

import absSyn.IAbsSyn;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.ProcedureDeclarationAbsSyn;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;
import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class ProcedureDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    public ProcedureDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public IAbsSyn toAbsSyn() {
        //TODO: implement
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> TODO = super.getListByType(TODO.class);
        IAbsSyn TODO2 = super.getOneByType(TODO2.class);

        return new Schnurzel(token, TODO, TODO2);
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.PROC) {
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();
                parseNext(new ParameterListConcSyn(getTokenList(), getCounter()));
                parseNext(new OptionalGlobalImportsConcSyn(getTokenList(), getCounter()));
                parseNext(new OptionalLocalStorageDeclarationsConcSyn(getTokenList(), getCounter()));
                if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                    consume();
                    parseNext(new BlockCmdConcSyn(getTokenList(), getCounter()));
                    if (getTokenList().getCurrent().getTerminal() == Terminal.ENDPROC) {
                        consume();

                    } else {
                        throwGrammarError();
                    }
                } else {
                    throwGrammarError();
                }
            } else {
                throwGrammarError();
            }
        }
    }
}