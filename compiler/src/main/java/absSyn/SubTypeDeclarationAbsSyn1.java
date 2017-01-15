package absSyn;

import context.Variable;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 28.12.2016.
 */
public class SubTypeDeclarationAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {
    private Terminal type;

    public SubTypeDeclarationAbsSyn1(Terminal type) {

        this.type = type;
    }

    @Override
    public void check() throws ContextError {
        //TODO Add Types to Tuple
        //für alle subtypes einen Eintrag in Variable erstellen.
        Variable currentVariable = Variable.getCurrentVariable();
        //TODO Leftsidetype liste abfüllen
        //currentVariable.addTuple(subType);

    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
