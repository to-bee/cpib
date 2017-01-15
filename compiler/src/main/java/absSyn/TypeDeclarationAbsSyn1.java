package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

/**
 * Created by ylaub on 26.12.2016.
 */
public class TypeDeclarationAbsSyn1 extends AbstractAbsSyn implements IAbsSyn {


    private final SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn;
    private final SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn1;
    private final OptionalTypeDeclarationAbsSyn optionalTypeDeclarationAbsSyn;

    public TypeDeclarationAbsSyn1(SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn, SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn1, OptionalTypeDeclarationAbsSyn optionalTypeDeclarationAbsSyn) {

        this.subTypeDeclarationAbsSyn = subTypeDeclarationAbsSyn;
        this.subTypeDeclarationAbsSyn1 = subTypeDeclarationAbsSyn1;
        this.optionalTypeDeclarationAbsSyn = optionalTypeDeclarationAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        subTypeDeclarationAbsSyn.check();
        subTypeDeclarationAbsSyn1.check();
        optionalTypeDeclarationAbsSyn.check();
    }

    @Override
    public int code(int location) throws ICodeArray.CodeTooSmallError {
        return location; // nothing to do
    }
}