using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Term2 : ITerm2
    {
        public ITerm3 Term3 { get; private set; }
        public IRepAddOprTerm3 RepAddOprTerm3 { get; private set; }

        public Term2 (ITerm3 term, IRepAddOprTerm3 opr)
        {
            Term3 = term;
            RepAddOprTerm3 = opr;
        }

        public IAbsExpression ToAbySyn()
        {
            return RepAddOprTerm3.ToAbsSyn(Term3.ToAbySyn());
        }
    }
}
