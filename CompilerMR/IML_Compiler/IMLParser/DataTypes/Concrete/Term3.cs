using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Term3 : ITerm3
    {
        public IFactor Factor { get; private set; }
        public IRepMultOprFactor RepMultOprFactor { get; private set; }

        public Term3(IFactor fac, IRepMultOprFactor opr)
        {
            Factor = fac;
            RepMultOprFactor = opr;
        }

        public IAbsExpression ToAbySyn()
        {
            return RepMultOprFactor.ToAbsSyn(Factor.ToAbySyn());
        }
    }
}
