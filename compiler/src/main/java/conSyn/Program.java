package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramAbsSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.IToken;
import scanner.tokenList.ITokenList;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by tobi on 17.12.16.
 */
public class Program extends AbstractConcSyn {
    private IToken token;

    public Program(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public IAbsSyn toAbsSyn() throws ContextError {
        //Für jedes Nichtterminalsymbol (unten mit ParseNext deklariert) wird eine Liste mit den dazugehörigen Elementen dem Abstrakten Syntaxbaum übergeben.
        List<IAbsSyn> OptionalGlobalDeclarations = super.getListByType(OptionalGlobalDeclarations.class);
        List<IAbsSyn> ProgramParameterList = super.getListByType(ProgramParameterList.class);
        IAbsSyn BlockCmd = super.getOneByType(BlockCmd.class);

        return new ProgramAbsSyn(token, ProgramParameterList, OptionalGlobalDeclarations,BlockCmd);
    }

    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.PROGRAM) {
            this.token = getTokenList().getCurrent();
            consume();
            if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT) {
                consume();

                parseNext(new ProgramParameterList(getTokenList(), getCounter()));
                parseNext(new OptionalGlobalDeclarations(getTokenList(), getCounter()));

                if (getTokenList().getCurrent().getTerminal() == Terminal.DO) {
                    consume();
                    parseNext(new BlockCmd(getTokenList(), getCounter()));
                    if (getTokenList().getCurrent().getTerminal() == Terminal.ENDPROGRAM) {
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
        } else {
            throwGrammarError();
        }
    }
}
