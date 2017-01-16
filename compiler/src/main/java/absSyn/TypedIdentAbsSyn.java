package absSyn;

import context.DefaultVar;
import context.Mode;
import context.Var;
import context.TupleVar;
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
            //store Changemode to var and reset to null for future use
            DefaultVar.getCurrentVariable().setChangemode(Mode.getCurrentMode());
            Mode.setCurrentMode(null);
        }
        else if (typeDeclarationAbsSyn.getSubType() instanceof TypeDeclarationAbsSyn1) {
            //TODO rewrite to TupleVar
            //wenn lparen --> dann tuple
            //DefaultVar erstellen mit ident
            TupleVar var = new TupleVar(ident);
            Var.setCurrentVariable(ident);
            Var.getCurrentVariable();
        }
        else {
            throw new ContextError("Check this case in TypedIdentAbsSyn");
        }

        typeDeclarationAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
