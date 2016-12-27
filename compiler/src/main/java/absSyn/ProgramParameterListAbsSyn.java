package absSyn;
import conSyn.OptionalProgramParamConcSyn;
import scanner.token.IToken;
import java.util.List;
/**
 * Created by ylaub on 26.12.2016.
 */
public class ProgramParameterListAbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final OptionalProgramParamAbsSyn optionalProgramParamConcSyn;

    public ProgramParameterListAbsSyn(OptionalProgramParamAbsSyn optionalProgramParamConcSyn) {
        this.optionalProgramParamConcSyn = optionalProgramParamConcSyn;
    }

    @Override
    public void check() {
        this.optionalProgramParamConcSyn.check();
    }
}
