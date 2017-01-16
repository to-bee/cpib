using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Term1 : ITerm1
    {
        public ITerm2 Term2 { get; private set; }
        public IRepRelOprTerm2 RepRelOprTerm2 { get; private set; }

        public Term1 (ITerm2 term, IRepRelOprTerm2 opr)
        {
            Term2 = term;
            RepRelOprTerm2 = opr;
        }

        public IAbsExpression ToAbySyn()
        {
            return RepRelOprTerm2.ToAbsSyn(Term2.ToAbySyn());
        }
    }
}
