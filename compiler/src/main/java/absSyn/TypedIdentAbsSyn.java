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

import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class TypedIdentAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final TypeDeclarationAbsSyn typeDeclarationAbsSyn;
    private final Ident ident;

    public TypedIdentAbsSyn(Ident ident, TypeDeclarationAbsSyn typeDeclarationAbsSyn) {
        this.ident = ident;
        this.typeDeclarationAbsSyn = typeDeclarationAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        if(typeDeclarationAbsSyn.getSubType() instanceof TypeDeclarationTypeAbsSyn) {
            Variable var = new Variable(ident, ((TypeDeclarationTypeAbsSyn) typeDeclarationAbsSyn.getSubType()).getType());
            Variable.addVariable(var);
        } else {
            throw new ContextError("Check this case");
        }

    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return 0;
    }
}
