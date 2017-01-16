using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepAddOprTerm3Eps : IRepAddOprTerm3
    {
        public IAbsExpression ToAbySyn(ITerm3 term3)
        {
            return term3.ToAbySyn();
        }

        public IAbsExpression ToAbsSyn(IAbsExpression expr)
        {
            return expr;
        }
    }

    class RepAddOprTerm3Opr : IRepAddOprTerm3
    {
        public AddOpr AddOpr { get; private set; }
        public ITerm3 Term3 { get; private set; }
        public IRepAddOprTerm3 RepAddOprTerm3 { get; private set; }

        public RepAddOprTerm3Opr (AddOpr opr, ITerm3 term, IRepAddOprTerm3 oprTerm)
        {
            AddOpr = opr;
            Term3 = term;
            RepAddOprTerm3 = oprTerm;
        }

        public IAbsExpression ToAbsSyn(IAbsExpression term3)
        {
            var corrAssoc = new AbsExpressionDyadic()
            {
                LeftExpression = term3,
                RightExpression = Term3.ToAbySyn(),
                Operator = AddOpr
            };

            return RepAddOprTerm3.ToAbsSyn(corrAssoc);
        }

        //public IAbsExpression ToAbsSyn(IAbsExpression exp)
        //{
        //    return new AbsExpressionDyadic()
        //    {
        //        LeftExpression = exp,
        //        RightExpression = Term3.ToAbySyn(),
        //        Operator = AddOpr
        //    };
        //}
    }
}
