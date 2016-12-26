package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ProgramParameterListAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> TODO;
    private final IAbsSyn TODO2;

    public Schnurzel(IToken t, List<IAbsSyn> TODO, IAbsSyn TODO2) {
        super(t);
        this.TODO = TODO;
        this.TODO2 = TODO2;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
