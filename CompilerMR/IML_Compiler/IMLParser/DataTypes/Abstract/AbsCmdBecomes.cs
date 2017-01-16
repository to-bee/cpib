using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsCmdBecomes : IAbsCmd
    {
        public IAbsExpression LeftExpression { get; set; }
        public IAbsExpression RightExpression { get; set; }

        public void Check()
        {
            LeftExpression.Check();
            RightExpression.Check();

            string lIdent = "";

            if (!(LeftExpression is AbsExpressionStore || LeftExpression is AbsExpressionIdent))
                throw new CheckerException("Left expression of an assignment must be an identifier");

            if (LeftExpression is AbsExpressionStore)
                lIdent = ((AbsExpressionStore) LeftExpression).Ident;
            else
                lIdent = ((AbsExpressionIdent) LeftExpression).Ident;

            if (LeftExpression.GetExprType() != RightExpression.GetExprType())
            {
                if (LeftExpression.GetExprType() == TYPE.INT64 && RightExpression.GetExprType() == TYPE.INT32)
                {
                    // one can assign a int32 to a int64 var, so don't throw an exception here...
                }
                else
                {
                    throw new CheckerException(
                    $"Left and right expression must have the same type on assignment calls, tried to assign an expression of type {RightExpression.GetExprType()} to {lIdent} of type {LeftExpression.GetExprType()}");
                }
                    
            }

            if (LeftExpression is AbsExpressionIdent)
            {
                var variable = GlobalContext.CurrentContext.GetVariable(((AbsExpressionIdent) LeftExpression).Ident);
                if (!variable.Initialized)
                {
                    //note: doesn't work as expected with imports...
                    //throw new CheckerException($"Variable {variable.Name} not initialized");
                }
                if (!variable.Writable)
                    throw new CheckerException($"Variable {variable.Name} is not writable");

            }
        }

        public int Code(int loc)
        {
            var loc1 = LeftExpression.Code(loc);
            SymbolTable.Deref = true;
            var loc2 = RightExpression.Code(loc1);
            SymbolTable.Deref = false;
            var loc3 = loc2 + 1;
            CodeGenerationContext.Instructions[loc2] = new Store();
            return loc3;
        }
    }
}
