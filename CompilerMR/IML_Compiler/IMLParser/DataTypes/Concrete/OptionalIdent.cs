using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalIdentEps : IOptionalIdent
    {
        public IAbsExpression ToAbySyn(Ident ident)
        {
            return new AbsExpressionIdent()
            {
                Ident = ident.value
            };
        }
    }

    class OptionalIdentInit : IOptionalIdent
    {
        public IAbsExpression ToAbySyn(Ident ident)
        {
            return new AbsExpressionStore() {Ident = ident.value};
        }
    }

    class OptionalIdentExpr : IOptionalIdent
    {
        public IExpressionList ExpressionList { get; private set; }

        public OptionalIdentExpr (IExpressionList list)
        {
            ExpressionList = list;
        }

        public IAbsExpression ToAbySyn(Ident ident)
        {
            return new AbsExpressionFuncCall() {Ident = ident.value, Parameter = ExpressionList.ToAbySyn()};
        }
    }
}
