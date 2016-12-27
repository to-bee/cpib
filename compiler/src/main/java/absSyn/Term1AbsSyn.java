package absSyn;

/**
 * Created by ylaub on 26.12.2016.
 */
public class Term1AbsSyn extends AbstractAbsSyn implements IAbsSyn{
    private final Term2AbsSyn term2AbsSyn;
    private final RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn;

    public Term1AbsSyn(Term2AbsSyn term2AbsSyn, RepRelOprTerm2AbsSyn repRelOprTerm2AbsSyn) {
        this.term2AbsSyn = term2AbsSyn;
        this.repRelOprTerm2AbsSyn = repRelOprTerm2AbsSyn;
    }

    @Override
    public void check() {
        this.term2AbsSyn.check();
        this.repRelOprTerm2AbsSyn.check();
    }
}

