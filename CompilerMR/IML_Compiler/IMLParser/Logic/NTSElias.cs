using IML_Compiler.IMLParser.DataTypes.Concrete;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.Logic
{
    public partial class Parser
    {
        public IRepeatingOptionalDeclarations repeatingOptionalDeclarations()
        {
            if (terminal != Terminals.SEMICOLON) return new RepeatingOptionalDeclarationsEps();

            Consume(Terminals.SEMICOLON);
            var d = declaration();
            var rod = repeatingOptionalDeclarations();
            return new RepeatingOptionalDeclarationsDecl(d, rod);
        }
        public IOptionalLocalStorageDeclarations optionalLocalStorageDeclarations()
        {
            if (terminal != Terminals.LOCAL) return new OptionalLocalStorageDeclarationsEps();

            Consume(Terminals.LOCAL);
            var sd = storageDeclaration();
            var rosd = repeatingOptionalStorageDeclarations();
            return new OptionalLocalStorageDeclarationsDecl(sd, rosd);
        }
        public IRepeatingOptionalStorageDeclarations repeatingOptionalStorageDeclarations()
        {
            if (terminal != Terminals.SEMICOLON) return new RepeatingOptionalStorageDeclarationsEps();


            Consume(Terminals.SEMICOLON);
            var sd = storageDeclaration();
            var rosd = repeatingOptionalStorageDeclarations();
            return new RepeatingOptionalStorageDeclarationsDecl(sd, rosd);
        }
        public IParameterList parameterList()
        {
            Consume(Terminals.LPAREN);
            var op = optionalParameters();
            Consume(Terminals.RPAREN);
            return new ParameterList(op);
        }
        public IOptionalParameters optionalParameters()
        {
            if (terminal == Terminals.FLOWMODE || terminal == Terminals.MECHMODE || terminal == Terminals.CHANGEMODE || terminal == Terminals.IDENT)
            {
                var p = parameter();
                var rop = repeatingOptionalParameters();
                return new OptionalParametersParams(p, rop);
            }
            return new OptionalParametersEps();
        }
        public IParameter parameter()
        {
            var fm = optionalFLOWMODE();
            var mm = optionalMECHMODE();
            var sd = storageDeclaration();
            return new Parameter(fm, mm, sd);
        }
        public IRepeatingOptionalParameters repeatingOptionalParameters()
        {
            if (terminal == Terminals.COMMA)
            {
                Consume(Terminals.COMMA);
                var p = parameter();
                var rop = repeatingOptionalParameters();
                return new RepeatingOptionalParametersParams(p, rop);
            }
            return new RepeatingOptionalParametersEps();
        }
        public IExpressionList expressionList()
        {
            Consume(Terminals.LPAREN);
            var op = optionalExpressions();
            Consume(Terminals.RPAREN);
            return new ExpressionList(op);
        }
        public IOptionalExpressions optionalExpressions()
        {
            if (terminal == Terminals.LITERAL || terminal == Terminals.IDENT || terminal == Terminals.LPAREN || terminal == Terminals.LCAST || terminal == Terminals.NOTOPER || terminal == Terminals.ADDOPR)
            {
                var e = expression();
                var roe = repeatingOptionalExpressions();
                return new OptionalExpressionsExpr(e, roe);
            }
            return new OptionalExpressionsEps();
        }
        public IExpression expression()
        {
            var t = term1();
            var rbt = repBOOLOPRterm1();
            return new Expression(t, rbt);
        }
        public IRepeatingOptionalExpressions repeatingOptionalExpressions()
        {
            if (terminal != Terminals.COMMA) return new RepeatingOptionalExpressionsEps();


            Consume(Terminals.COMMA);
            var e = expression();
            var roe = repeatingOptionalExpressions();
            return new RepeatingOptionalExpressionsExpr(e, roe);
        }
        public IRepBoolOprTerm1 repBOOLOPRterm1()
        {
            if (terminal != Terminals.BOOLOPR) return new RepBoolOprTerm1Eps();


            var o = token;
            Consume(Terminals.BOOLOPR);
            var t = term1();
            var rbt = repBOOLOPRterm1();
            return new RepBoolOprTerm1Opr((BoolOpr)o, t, rbt);
        }
        public ITerm1 term1()
        {
            var r = term2();
            var rrt = repRELOPRterm2();
            return new Term1(r, rrt);
        }
        public IRepRelOprTerm2 repRELOPRterm2()
        {
            if (terminal != Terminals.RELOPR) return new RepRelOprTerm2Eps();


            var o = token;
            Consume(Terminals.RELOPR);
            var t = term2();
            var rrt = repRELOPRterm2();
            return new RepRelOprTerm2Opr((RelOpr)o, t, rrt);
        }
        public ITerm2 term2()
        {
            var t = term3();
            var rat = repADDOPRterm3();
            return new Term2(t, rat);
        }
        public IRepAddOprTerm3 repADDOPRterm3()
        {
            if (terminal != Terminals.ADDOPR) return new RepAddOprTerm3Eps();


            var o = token;
            Consume(Terminals.ADDOPR);
            var t = term3();
            var rat = repADDOPRterm3();
            return new RepAddOprTerm3Opr((AddOpr)o, t, rat);
        }
        public ITerm3 term3()
        {
            var f = factor();
            var rmf = repMULTOPRfactor();
            return new Term3(f, rmf);
        }
        public IRepMultOprFactor repMULTOPRfactor()
        {
            if (terminal == Terminals.MULTOPR)
            {
                var o = token;
                Consume(Terminals.MULTOPR);
                var f = factor();
                var rmf = repMULTOPRfactor();
                return new RepMultOprFactorOpr((MultOpr)o, f, rmf);
            }
            return new RepMultOprFactorEps();
        }

        public IFactor factor()
        {
            switch (terminal)
            {
                case Terminals.LITERAL:
                    {
                        var l = token;
                        Consume(Terminals.LITERAL);
                        return new FactorLiteral((Literal)l);
                    }
                case Terminals.IDENT:
                    {
                        var i = token;
                        Consume(Terminals.IDENT);
                        var oi = optionalIdent();
                        return new FactorIdent((Ident)i, oi);
                    }
                case Terminals.NOTOPER:
                case Terminals.ADDOPR:
                    {
                        var mo = monadicOperator();
                        var f = factor();
                        return new FactorMonadic(mo, f);
                    }
                case Terminals.LPAREN:
                    {
                        Consume(Terminals.LPAREN);
                        var e = expression();
                        Consume(Terminals.RPAREN);
                        return new FactorExpr(e);
                    }
                case Terminals.LCAST:
                    {
                        var c = casting();
                        var f = factor();
                        return new FactorCasting(c, f);
                    }
                default:
                    throw new ParserException("factor");
            }
        }
        public IOptionalIdent optionalIdent()
        {
            if (terminal == Terminals.INIT)
            {
                Consume(Terminals.INIT);
                return new OptionalIdentInit();
            }
            else if (terminal == Terminals.LPAREN)
            {
                var el = expressionList();
                return new OptionalIdentExpr(el);
            }
            return new OptionalIdentEps();
        }
        public IMonadicOperator monadicOperator()
        {
            if (terminal == Terminals.NOTOPER)
            {
                Consume(Terminals.NOTOPER);
                return new MonadicOperatorNot();
            }
            else if (terminal == Terminals.ADDOPR)
            {
                var o = token;
                Consume(Terminals.ADDOPR);
                return new MonadicOperatorAdd((AddOpr)o);
            }
            else
            {
                throw new ParserException("monadicOperator");
            }
        }
        public ICasting casting()
        {
            Consume(Terminals.LCAST);
            var t = token;
            Consume(Terminals.TYPE);
            Consume(Terminals.RCAST);

            return new Casting((IMLScanner.DataTypes.Type)t);
        }
    }
}
