using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class CmdSkip : ICmd
    {
        public IAbsCmd ToAbySyn()
        {
            return new AbsSkipCmd();
        }
    }

    class CmdBecomes : ICmd
    {
        public IExpression ExpressionA { get; private set; }
        public IExpression ExpressionB { get; private set; }

        public CmdBecomes(IExpression exprA, IExpression exprB)
        {
            ExpressionA = exprA;
            ExpressionB = exprB;
        }

        public IAbsCmd ToAbySyn()
        {
            return new AbsCmdBecomes()
            {
                LeftExpression = ExpressionA.ToAbySyn(),
                RightExpression = ExpressionB.ToAbySyn()
            };
        }
    }

    class CmdIf : ICmd
    {
        public IExpression Expression { get; private set; }
        public IBlockCmd BlockCmdIf { get; private set; }
        public IOptionalElse OptionalElse { get; private set; }

        public CmdIf (IExpression expr, IBlockCmd ifBlock, IOptionalElse elseBlock)
        {
            Expression = expr;
            BlockCmdIf = ifBlock;
            OptionalElse = elseBlock;
        }

        public IAbsCmd ToAbySyn()
        {
            return new AbsCmdIf()
            {
                BlockCmd = BlockCmdIf.ToAbySyn(),
                Condition = Expression.ToAbySyn(),
                ElseBlockCmd = OptionalElse.ToAbySyn()
            };
        }
    }

    class CmdWhile : ICmd
    {
        public IExpression Expression { get; private set; }
        public IBlockCmd BlockCmd { get; private set; }

        public CmdWhile(IExpression expr, IBlockCmd block)
        {
            Expression = expr;
            BlockCmd = block;
        }

        public IAbsCmd ToAbySyn()
        {
            return new AbsCmdWhile() {BlockCmd = BlockCmd.ToAbySyn(), Condition = Expression.ToAbySyn()};
        }
    }

    class CmdCall : ICmd
    {
        public IExpressionList ExpressionList { get; private set; }
        public IOptionalGlobalInits OptionalGlobalInits { get; private set; }
        public Ident Ident { get; private set; }

        public CmdCall(Ident ident, IExpressionList list, IOptionalGlobalInits inits)
        {
            Ident = ident;
            ExpressionList = list;
            OptionalGlobalInits = inits;
        }

        public IAbsCmd ToAbySyn()
        {
            return new AbsCmdCall()
            {
                ExpressionList = ExpressionList.ToAbySyn(),
                GlobalInits = OptionalGlobalInits.ToAbySyn(),
                Ident = Ident.value
            };
        }
    }

    class CmdDebugIn : ICmd
    {
        public IExpression Expression { get; private set; }

        public CmdDebugIn(IExpression expr)
        {
            Expression = expr;
        }

        public IAbsCmd ToAbySyn()
        {
            return new AbsCmdDebug() {Expression = Expression.ToAbySyn(), In = true};
        }
    }

    class CmdDebugOut : ICmd
    {
        public IExpression Expression { get; private set; }

        public CmdDebugOut(IExpression expr)
        {
            Expression = expr;
        }

        public IAbsCmd ToAbySyn()
        {
            return new AbsCmdDebug() {Expression = Expression.ToAbySyn(), In = false};
        }
    }
}
