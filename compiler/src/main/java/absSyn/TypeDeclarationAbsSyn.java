package absSyn;

import conSyn.TypeDeclarationTypeConcSyn;
import context.Variable;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class TypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn {
    public IAbsSyn getSubType() {
        return subType;
    }

    private IAbsSyn subType;

    public TypeDeclarationAbsSyn(IAbsSyn subType) {

        this.subType = subType;
    }

    @Override
    public void check() throws ContextError {
        //Wenn Tuple, dann weiter checken
        if (subType instanceof TypeDeclarationAbsSyn1) {
            subType.check();
        }
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location; // nothing to do
    }
}