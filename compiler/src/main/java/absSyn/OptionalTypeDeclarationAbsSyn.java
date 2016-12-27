package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class OptionalTypeDeclarationAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> SubTypeDeclarationConcSyn;

    public OptionalTypeDeclarationAbsSyn(IToken t, List<IAbsSyn> SubTypeDeclarationConcSyn) {
        super(t);
        this.SubTypeDeclarationConcSyn = SubTypeDeclarationConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}