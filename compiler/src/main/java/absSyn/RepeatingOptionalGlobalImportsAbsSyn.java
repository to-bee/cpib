package absSyn;
import conSyn.IConcSyn;
import scanner.datatypes.Terminal;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class RepeatingOptionalGlobalImportsAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final List<IAbsSyn> GlobalImportConcSyn;
    private final List<IAbsSyn> RepeatingOptionalGlobalImportsConcSyn;

    public RepeatingOptionalGlobalImportsAbsSyn(IToken t, List<IAbsSyn> GlobalImportConcSyn, List<IAbsSyn> RepeatingOptionalGlobalImportsConcSyn) {
        super(t);
        this.GlobalImportConcSyn = GlobalImportConcSyn;
        this.RepeatingOptionalGlobalImportsConcSyn = RepeatingOptionalGlobalImportsConcSyn;
    }

    @Override
    public void check() {
        //TODO: Implement Scope Check and Type Check
    }
}
