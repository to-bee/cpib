package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class TypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> SubTypeDeclarationConcSyn;
    private final List<IAbsSyn> OptionalTypeDeclarationConcSyn;

    public TypeDeclarationAbsSyn(IToken t, List<IAbsSyn> SubTypeDeclarationConcSyn, List<IAbsSyn> OptionalTypeDeclarationConcSyn) {
        super(t);
        this.SubTypeDeclarationConcSyn = SubTypeDeclarationConcSyn;
        this.OptionalTypeDeclarationConcSyn = OptionalTypeDeclarationConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
