package absSyn;

import context.AbstractVar;
import context.DefaultVariable;
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
            DefaultVariable var = new DefaultVariable(ident, ((TypeDeclarationTypeAbsSyn) typeDeclarationAbsSyn.getSubType()).getType());
            AbstractVar.addVariable(var);
            DefaultVariable.setCurrentVariable(ident);
        }
        else if (typeDeclarationAbsSyn.getSubType() instanceof TypeDeclarationAbsSyn1) {
            //wenn lparen --> dann tuple
            //DefaultVariable erstellen mit ident
            TupleVar var = new TupleVar(ident);
            DefaultVariable.setCurrentVariable(ident);
            DefaultVariable.getCurrentVariable();
            //ArrayList<DefaultVariable> vars = new ArrayList<DefaultVariable>();

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
