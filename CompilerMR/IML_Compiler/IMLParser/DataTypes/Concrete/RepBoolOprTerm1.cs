using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepBoolOprTerm1Eps : IRepBoolOprTerm1
    {
        public IAbsExpression ToAbsSyn(IAbsExpression term1)
        {
            return term1;
        }
    }

    class RepBoolOprTerm1Opr : IRepBoolOprTerm1
    {
        public BoolOpr BoolOpr { get; private set; }
        public ITerm1 Term1 { get; private set; }
        public IRepBoolOprTerm1 RepBoolOprTerm1 { get; private set; }

        public RepBoolOprTerm1Opr (BoolOpr opr, ITerm1 term, IRepBoolOprTerm1 termopr)
        {
            BoolOpr = opr;
            Term1 = term;
            RepBoolOprTerm1 = termopr;
        }

        public IAbsExpression ToAbsSyn(IAbsExpression term1)
        {
            var corrAssoc = new AbsExpressionDyadic()
            {
                LeftExpression = term1,
                RightExpression = Term1.ToAbySyn(),
                Operator = BoolOpr
            };
            return RepBoolOprTerm1.ToAbsSyn(corrAssoc);
        }
    }
}
