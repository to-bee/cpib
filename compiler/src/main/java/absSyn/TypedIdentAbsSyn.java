package absSyn;

import context.*;
import scanner.errors.ContextError;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class TypedIdentAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    private final TypeDeclarationAbsSyn typeDeclarationAbsSyn;
    private final Ident ident;

    public TypedIdentAbsSyn(Ident ident, TypeDeclarationAbsSyn typeDeclarationAbsSyn) {
        this.ident = ident;
        this.typeDeclarationAbsSyn = typeDeclarationAbsSyn;
    }
    public String toString(int counter) {
        return "TypedIdentAbsSyn:\r\n\t" + this.ident + "," + typeDeclarationAbsSyn.toString(counter);
    }

    @Override
    public void check() throws ContextError {
        if (typeDeclarationAbsSyn.getSubType() instanceof TypeDeclarationTypeAbsSyn) {
            DefaultVar var = new DefaultVar(ident, ((TypeDeclarationTypeAbsSyn) typeDeclarationAbsSyn.getSubType()).getType());
            Var.addVariable(var);
            DefaultVar.setCurrentVariable(ident);
        }
        else if (typeDeclarationAbsSyn.getSubType() instanceof TypeDeclarationAbsSyn1) {
            TupleVar var = new TupleVar(ident);
            Var.addVariable(var);
            DefaultVar.setCurrentVariable(ident);
        }
        else {
            throw new ContextError("Check this case in TypedIdentAbsSyn");
        }

        Context.addVmVariable(ident);
        typeDeclarationAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {

        return 0;
    }
}
