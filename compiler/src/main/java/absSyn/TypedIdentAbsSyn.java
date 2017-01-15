package absSyn;

import context.DefaultVar;
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

    @Override
    public void check() throws ContextError {

        if (typeDeclarationAbsSyn.getSubType() instanceof TypeDeclarationTypeAbsSyn) {
            DefaultVar var = new DefaultVar(ident, ((TypeDeclarationTypeAbsSyn) typeDeclarationAbsSyn.getSubType()).getType());
            Var.addVariable(var);
            DefaultVar.setCurrentVariable(ident);
        }
        else if (typeDeclarationAbsSyn.getSubType() instanceof TypeDeclarationAbsSyn1) {
            //wenn lparen --> dann tuple
            //DefaultVar erstellen mit ident
            TupleVar var = new TupleVar(ident);
            DefaultVar.setCurrentVariable(ident);
            DefaultVar.getCurrentVariable();
            //ArrayList<DefaultVar> vars = new ArrayList<DefaultVar>();

            //TODO variablenliste erstellen
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
