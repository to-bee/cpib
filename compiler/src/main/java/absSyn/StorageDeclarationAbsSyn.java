package absSyn;

import context.Context;
import context.Scope;
import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class StorageDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final TypedIdentAbsSyn typedIdentAbsSyn;
    private final OptionalChangeModeAbsSyn optionalChangeModeAbsSyn;

    public StorageDeclarationAbsSyn(TypedIdentAbsSyn typedIdentAbsSyn, OptionalChangeModeAbsSyn optionalChangeModeAbsSyn) {

        this.typedIdentAbsSyn = typedIdentAbsSyn;
        this.optionalChangeModeAbsSyn = optionalChangeModeAbsSyn;
    }

    public String toString(int counter) {
        return "StorageDeclarationAbsSyn:\r\n\t" + typedIdentAbsSyn.toString(counter) + "," + optionalChangeModeAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        Context.setCurrentContext(new Context(Scope.LOCAL));

        this.typedIdentAbsSyn.check();
        this.optionalChangeModeAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}

