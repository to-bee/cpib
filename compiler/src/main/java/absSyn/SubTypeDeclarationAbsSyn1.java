package absSyn;

import context.Var;
import context.DefaultVar;
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
    public String toString(int counter) {
        return "SubTypeDeclarationAbsSyn1:\r\n\t" + this.type;
    }

    @Override
    public void check() throws ContextError {
        //TODO Add Types to Tuple
        //für alle subtypes einen Eintrag in DefaultVar erstellen.
        DefaultVar currentVariable = (DefaultVar) Var.getCurrentVariable();
        //TODO Leftsidetype liste abfüllen
        //currentVariable.addTuple(subType);

    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
