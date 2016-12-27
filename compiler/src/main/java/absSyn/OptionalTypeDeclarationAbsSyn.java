package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalTypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn;

    public OptionalTypeDeclarationAbsSyn(SubTypeDeclarationAbsSyn subTypeDeclarationAbsSyn) {

        this.subTypeDeclarationAbsSyn = subTypeDeclarationAbsSyn;
    }

    @Override
    public void check() throws ContextError {
        this.subTypeDeclarationAbsSyn.check();
    }
}
