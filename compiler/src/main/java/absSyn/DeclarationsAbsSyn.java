package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class DeclarationsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> DeclarationConcSyn;
    private final List<IAbsSyn> RepeatingOptionalDeclarationsConcSyn;

    public DeclarationsAbsSyn(IToken t, List<IAbsSyn> DeclarationConcSyn, List<IAbsSyn> RepeatingOptionalDeclarationsConcSyn) {
        super(t);
        this.DeclarationConcSyn = DeclarationConcSyn;
        this.RepeatingOptionalDeclarationsConcSyn = RepeatingOptionalDeclarationsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
