package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramAbsSyn;
import scanner.datatypes.Terminal;
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
    public IAbsSyn toAbsSyn() {


        List<IConcSyn> optionalGlobalDeclarationList = new ArrayList<>();
        try {
            optionalGlobalDeclarationList = this.getChilds().stream().filter(c -> c.getClass() == OptionalGlobalDeclarations.class).collect(Collectors.toList());
        } catch (NoSuchElementException e) {

        }

        List<IConcSyn> optionalProgramParameterList = new ArrayList<>();
        try {
            optionalProgramParameterList = this.getChilds().stream().filter(c -> c.getClass() == ProgramParameterList.class).collect(Collectors.toList());
        } catch (NoSuchElementException e) {

        }

        IConcSyn blockCmd = this.getChilds().stream().filter(c -> c.getClass() == BlockCmd.class).findFirst().get();
        return new ProgramAbsSyn(token, optionalGlobalDeclarationList, optionalProgramParameterList, blockCmd);
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
