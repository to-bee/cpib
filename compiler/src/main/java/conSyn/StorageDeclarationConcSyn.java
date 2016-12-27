package conSyn;

import absSyn.IAbsSyn;
import absSyn.ProgramParameterListAbsSyn;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
import scanner.datatypes.Terminal;

import absSyn.StorageDeclarationAbsSyn;
import scanner.token.IToken;

import java.util.List;
/**
 * Created by tobi on 17.12.16.
 */
public class StorageDeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private TypedIdentConcSyn typedIdentConcSyn;
    private OptionalChangeModeConcSyn optionalChangeModeConcSyn;

    public StorageDeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    private IToken token;
    @Override
    public StorageDeclarationAbsSyn toAbsSyn()throws ContextError {
        return new StorageDeclarationAbsSyn(typedIdentConcSyn.toAbsSyn(), optionalChangeModeConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            optionalChangeModeConcSyn = new OptionalChangeModeConcSyn(getTokenList(), getCounter());
            parseNext(optionalChangeModeConcSyn);

            typedIdentConcSyn = new TypedIdentConcSyn(getTokenList(), getCounter())
            parseNext(typedIdentConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
