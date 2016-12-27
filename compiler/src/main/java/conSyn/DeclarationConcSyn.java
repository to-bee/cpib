package conSyn;

import absSyn.DeclarationAbsSyn;
import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;
/**
 * Created by tobi on 17.12.16.
 */
public class DeclarationConcSyn extends AbstractConcSyn implements IConcSyn {
    private StorageDeclarationConcSyn storageDeclarationConcSyn;
    private FunctionDeclarationConcSyn functionDeclarationConcSyn;
    private ProcedureDeclarationConcSyn procedureDeclarationConcSyn;

    public DeclarationConcSyn(ITokenList tokenList, int i) {
        super(tokenList, i);
    }

    @Override
    public DeclarationAbsSyn toAbsSyn() throws ContextError {

        return new DeclarationAbsSyn(storageDeclarationConcSyn.toAbsSyn(),functionDeclarationConcSyn.toAbsSyn(),procedureDeclarationConcSyn.toAbsSyn());
    }


    @Override
    public void parse() throws GrammarError {
        if (getTokenList().getCurrent().getTerminal() == Terminal.IDENT
                || getTokenList().getCurrent().getTerminal().getType() == TerminalType.CHANGEMODE) {
            storageDeclarationConcSyn = new StorageDeclarationConcSyn(getTokenList(), getCounter());
            parseNext(storageDeclarationConcSyn);
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.FUN) {
            functionDeclarationConcSyn = new FunctionDeclarationConcSyn(getTokenList(), getCounter());
            parseNext(functionDeclarationConcSyn);
        } else if (getTokenList().getCurrent().getTerminal() == Terminal.PROC) {
            procedureDeclarationConcSyn = new ProcedureDeclarationConcSyn(getTokenList(), getCounter());
            parseNext(procedureDeclarationConcSyn);
        } else {
            throwGrammarError();
        }
    }
}
