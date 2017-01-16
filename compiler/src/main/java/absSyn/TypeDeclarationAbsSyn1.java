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
    public String toString(int counter) {
        return "TypeDeclarationAbsSyn1:\r\n\t" + subTypeDeclarationAbsSyn.toString(counter) + "," + subTypeDeclarationAbsSyn1.toString(counter) + "," + optionalTypeDeclarationAbsSyn.toString(counter);
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