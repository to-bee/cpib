package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class TypedIdentAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> TypeDeclarationConcSyn;

    public TypedIdentAbsSyn(IToken t, List<IAbsSyn> TypeDeclarationConcSyn) {
        super(t);
        this.TypeDeclarationConcSyn = TypeDeclarationConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
