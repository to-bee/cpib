package absSyn;

import conSyn.IConcSyn;
import conSyn.TypeDeclarationTypeConcSyn;
import context.Context;
import context.Variable;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

import java.util.ArrayList;
import java.util.List;

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
            Variable var = new Variable(ident, ((TypeDeclarationTypeAbsSyn) typeDeclarationAbsSyn.getSubType()).getType());
            Variable.addVariable(var);
            Variable.setCurrentVariable(ident);
        } else if (typeDeclarationAbsSyn.getSubType() instanceof TypeDeclarationAbsSyn1) {
            //wenn lparen --> dann tuple
            //Variable erstellen mit ident
            Variable var = new Variable(ident);
            Variable.setCurrentVariable(ident);
            Variable.getCurrentVariable();

            //wenn ident = null --> anonymes tupel
            //ArrayList<Variable> vars = new ArrayList<Variable>();

            //TODO variablenliste erstellen
        } else {
            throw new ContextError("Check this case in TypedIdentAbsSyn");
        }
        typeDeclarationAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
