using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class ExpressionList : IExpressionList
    {
        public IOptionalExpressions OptionalExpressions { get; private set; }

        public ExpressionList (IOptionalExpressions expr)
        {
            OptionalExpressions = expr;
        }

        public IAbsList<IAbsExpression> ToAbySyn()
        {
            return new AbsExpressionList() {ExpressionList = OptionalExpressions.ToAbySyn()};
        }
    }
}
