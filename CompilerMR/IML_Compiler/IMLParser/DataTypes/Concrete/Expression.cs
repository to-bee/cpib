using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Expression : IExpression
    {
        public ITerm1 Term1 { get; private set; }
        public IRepBoolOprTerm1 RepBoolOprTerm1 { get; private set; }

        public Expression (ITerm1 term1, IRepBoolOprTerm1 opr)
        {
            Term1 = term1;
            RepBoolOprTerm1 = opr;
        }

        public IAbsExpression ToAbySyn()
        {
            return RepBoolOprTerm1.ToAbsSyn(Term1.ToAbySyn());
        }
    }
}
