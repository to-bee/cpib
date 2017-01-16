using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepMultOprFactorEps : IRepMultOprFactor
    {
        public IAbsExpression ToAbsSyn(IAbsExpression factor)
        {
            return factor;
        }
    }

    class RepMultOprFactorOpr : IRepMultOprFactor
    {
        public MultOpr MultOpr { get; private set; }
        public IFactor Factor { get; private set; }
        public IRepMultOprFactor RepMultOprFactor { get; private set; }

        public RepMultOprFactorOpr (MultOpr opr, IFactor fac, IRepMultOprFactor oprFac)
        {
            MultOpr = opr;
            Factor = fac;
            RepMultOprFactor = oprFac;
        }

        public IAbsExpression ToAbsSyn(IAbsExpression factor)
        {
            var corrAssoc = new AbsExpressionDyadic()
            {
                LeftExpression = factor,
                RightExpression = Factor.ToAbySyn(),
                Operator = MultOpr
            };
            return RepMultOprFactor.ToAbsSyn(corrAssoc);
        }
    }
}
