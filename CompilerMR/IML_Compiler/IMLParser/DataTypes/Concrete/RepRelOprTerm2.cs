using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepRelOprTerm2Eps : IRepRelOprTerm2
    {
        public IAbsExpression ToAbsSyn(IAbsExpression term2)
        {
            return term2;
        }
    }

    class RepRelOprTerm2Opr : IRepRelOprTerm2
    {
        public RelOpr RelOpr { get; private set; }
        public ITerm2 Term2 { get; private set; }
        public IRepRelOprTerm2 RepRelOprTerm2 { get; private set; }

        public RepRelOprTerm2Opr (RelOpr opr, ITerm2 term, IRepRelOprTerm2 oprTerm)
        {
            RelOpr = opr;
            Term2 = term;
            RepRelOprTerm2 = oprTerm;
        }

        public IAbsExpression ToAbsSyn(IAbsExpression term2)
        {
            var corrAssoc = new AbsExpressionDyadic()
            {
                LeftExpression = term2,
                RightExpression = Term2.ToAbySyn(),
                Operator = RelOpr
            };
            return RepRelOprTerm2.ToAbsSyn(corrAssoc);
        }
    }
}
