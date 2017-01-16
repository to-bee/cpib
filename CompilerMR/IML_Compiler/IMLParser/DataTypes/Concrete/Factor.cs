using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class FactorLiteral : IFactor
    {
        public Literal Literal { get; private set; }

        public FactorLiteral (Literal lit)
        {
            Literal = lit;
        }

        public IAbsExpression ToAbySyn()
        {
            return new AbsExpressionLiteral() {Literal = Literal};
        }
    }

    class FactorIdent : IFactor
    {
        public Ident Ident { get; private set; }
        public IOptionalIdent OptionalIdent { get; private set; }

        public FactorIdent (Ident ident, IOptionalIdent idents)
        {
            Ident = ident;
            OptionalIdent = idents;
        }

        public IAbsExpression ToAbySyn()
        {
            return OptionalIdent.ToAbySyn(Ident);
        }
    }

    class FactorMonadic : IFactor
    {
        public IMonadicOperator MonadicOperator { get; private set; }
        public IFactor Factor { get; private set; }

        public FactorMonadic (IMonadicOperator opr, IFactor fac)
        {
            MonadicOperator = opr;
            Factor = fac;
        }

        public IAbsExpression ToAbySyn()
        {
            return new AbsExpressionMonadic() {Operator = MonadicOperator.ToAbySyn(), Expression = Factor.ToAbySyn()};
        }
    }

    class FactorExpr : IFactor
    {
        public IExpression Expression { get; private set; }

        public FactorExpr (IExpression expr)
        {
            Expression = expr;
        }

        public IAbsExpression ToAbySyn()
        {
            return new AbsExpressionExpr() {Expression = Expression.ToAbySyn()};
        }
    }

    class FactorCasting : IFactor
    {
        public ICasting Casting { get; private set; }
        public IFactor Factor { get; private set; }

        public FactorCasting (ICasting cast, IFactor fac)
        {
            Casting = cast;
            Factor = fac;
        }

        public IAbsExpression ToAbySyn()
        {
            return new AbsExpressionCasting() {CastingToType = Casting.ToAbySyn(), Expression = Factor.ToAbySyn()};
        }
    }
}
