using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepeatingOptionalExpressionsEps : IRepeatingOptionalExpressions
    {
        public IAbsList<IAbsExpression> ToAbySyn()
        {
            return new AbsEmptyList<IAbsExpression>();
        }
    }

    class RepeatingOptionalExpressionsExpr : IRepeatingOptionalExpressions
    {
        public IExpression Expression { get; private set; }
        public IRepeatingOptionalExpressions RepeatingOptionalExpressions { get; private set; }

        public RepeatingOptionalExpressionsExpr (IExpression expr, IRepeatingOptionalExpressions exprs)
        {
            Expression = expr;
            RepeatingOptionalExpressions = exprs;
        }

        public IAbsList<IAbsExpression> ToAbySyn()
        {
            return new AbsExpressionList()
            {
                Expression = Expression.ToAbySyn(),
                ExpressionList = RepeatingOptionalExpressions.ToAbySyn()
            };
        }
    }
}
