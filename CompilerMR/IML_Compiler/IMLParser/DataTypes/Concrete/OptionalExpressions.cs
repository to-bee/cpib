using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalExpressionsEps : IOptionalExpressions
    {
        public IAbsList<IAbsExpression> ToAbySyn()
        {
            return new AbsEmptyList<IAbsExpression>();
        }
    }

    class OptionalExpressionsExpr : IOptionalExpressions
    {
        public IExpression Expression { get; private set; }
        public IRepeatingOptionalExpressions RepeatingOptionalExpressions { get; private set; }

        public OptionalExpressionsExpr (IExpression expr, IRepeatingOptionalExpressions rexprs)
        {
            Expression = expr;
            RepeatingOptionalExpressions = rexprs;
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
