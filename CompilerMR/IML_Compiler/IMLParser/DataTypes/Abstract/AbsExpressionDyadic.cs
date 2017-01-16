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
    public class AbsExpressionDyadic : IAbsExpression
    {
        public IAbsExpression LeftExpression { get; set; }
        public IAbsExpression RightExpression { get; set; }
        public Token Operator { get; set; }
        public void Check()
        {
            LeftExpression.Check();
            RightExpression.Check();

            if (LeftExpression.GetExprType() != RightExpression.GetExprType())
            {
                throw new CheckerException("Leftexpressîon has not the same type as the rightexpression");
            }

            var ltype = LeftExpression.GetExprType();

            if (Operator is BoolOpr)
            {
                if (ltype != TYPE.BOOL)
                    throw new CheckerException($"Bool operator not allowed for type {ltype}");
            }
            else if (Operator is MultOpr)
            {
                if (!(ltype == TYPE.FLOAT || ltype == TYPE.INT32 || ltype == TYPE.INT64))
                    throw new CheckerException($"Multiplication not allowed for type {ltype}");
            }
            else if (Operator is AddOpr)
            {
                if (!(ltype == TYPE.FLOAT || ltype == TYPE.INT32 || ltype == TYPE.INT64))
                    throw new CheckerException($"Addition not allowed for type {ltype}");
            }
            else if (Operator is RelOpr)
            {
                //if (ltype == TYPE.BOOL)
                //    throw new CheckerException($"Relational operators not defined for bool operands");
            }
            else
            {
                throw new CheckerException(
                    "unexpected operator in dyadic expression -> imho, this exception should never be thrown :: " +
                    Operator);
            }
        }

        public int Code(int loc)
        {
            SymbolTable.Deref = true;
            var loc1 = LeftExpression.Code(loc);
            //var loc2 = 0;
            var endLoc = -1;

            SymbolTable.Deref = true;
            if (Operator is BoolOpr)
            { // &&,||,&?,|?
                var opr = (Operator as BoolOpr).value;
                if (opr == BOOLOPR.AND)
                {
                    var loc2 = RightExpression.Code(loc1);
                    CodeGenerationContext.Instructions[loc2] = new AddInt();
                    CodeGenerationContext.Instructions[loc2 + 1] = new LoadImInt() { Value = 2 };
                    CodeGenerationContext.Instructions[loc2 + 2] = new EqInt();
                    endLoc = loc2 + 3;
                }
                else if (opr == BOOLOPR.OR)
                {
                    var loc2 = RightExpression.Code(loc1);
                    CodeGenerationContext.Instructions[loc2] = new AddInt();
                    CodeGenerationContext.Instructions[loc2 + 1] = new LoadImInt() { Value = 0 };
                    CodeGenerationContext.Instructions[loc2 + 2] = new GtInt();
                    endLoc = loc2 + 3;
                }
                else if (opr == BOOLOPR.CAND)
                {
                    var loc2 = loc1 + 1;
                    var loc3 = RightExpression.Code(loc2);
                    var loc4 = loc3 + 1;
                    CodeGenerationContext.Instructions[loc3] = new UncondJump() { Address = loc4 + 1 };
                    CodeGenerationContext.Instructions[loc1] = new CondJump() { Address = loc4 };
                    CodeGenerationContext.Instructions[loc4] = new LoadImInt() { Value = 0 };
                    endLoc = loc4 + 1;
                }
                else if (opr == BOOLOPR.COR)
                {
                    var loc2 = loc1 + 1;
                    var loc3 = loc2 + 2;
                    var loc4 = RightExpression.Code(loc3);
                    var loc5 = loc4 + 1;
                    var loc6 = loc5 + 1;
                    CodeGenerationContext.Instructions[loc1] = new CondJump() { Address = loc3 };
                    CodeGenerationContext.Instructions[loc2] = new UncondJump() { Address = loc5 };
                    CodeGenerationContext.Instructions[loc4] = new UncondJump() { Address = loc6 };
                    CodeGenerationContext.Instructions[loc5] = new LoadImInt() { Value = 1 };
                    endLoc = loc6;
                }
            }
            else if (Operator is MultOpr)
            {
                var opr = (Operator as MultOpr).value;
                var loc2 = RightExpression.Code(loc1);
                IInstruction i = null;

                if (opr == MULTOPR.DIV_E)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new DivTruncFloat();
                            break;
                        case TYPE.INT32:
                            i = new DivTruncInt();
                            break;
                        case TYPE.INT64:
                            i = new DivTruncLong();
                            break;
                    }
                }
                else if (opr == MULTOPR.MOD_E)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new ModTruncFloat();
                            break;
                        case TYPE.INT32:
                            i = new ModTruncInt();
                            break;
                        case TYPE.INT64:
                            i = new ModTruncLong();
                            break;
                    }
                }
                else if (opr == MULTOPR.TIMES)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new MultFloat();
                            break;
                        case TYPE.INT32:
                            i = new MultInt();
                            break;
                        case TYPE.INT64:
                            i = new MultLong();
                            break;
                    }
                }
                endLoc = loc2 + 1;
                CodeGenerationContext.Instructions[loc2] = i;
            }
            else if (Operator is AddOpr)
            {
                var opr = (Operator as AddOpr).value;
                var loc2 = RightExpression.Code(loc1);
                IInstruction i = null;

                if (opr == ADDOPR.MINUS)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new SubFloat();
                            break;
                        case TYPE.INT32:
                            i = new SubInt();
                            break;
                        case TYPE.INT64:
                            i = new SubLong();
                            break;
                    }
                }
                else if (opr == ADDOPR.PLUS)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new AddFloat();
                            break;
                        case TYPE.INT32:
                            i = new AddInt();
                            break;
                        case TYPE.INT64:
                            i = new AddLong();
                            break;
                    }
                }
                endLoc = loc2 + 1;
                CodeGenerationContext.Instructions[loc2] = i;
            }
            else if (Operator is RelOpr)
            {
                var loc2 = RightExpression.Code(loc1);
                var opr = (Operator as RelOpr).value;
                IInstruction i = null;

                if (opr == RELOPR.EQ)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new EqFloat();
                            break;
                        case TYPE.INT32:
                        case TYPE.BOOL:
                            i = new EqInt();
                            break;
                        case TYPE.INT64:
                            i = new EqLong();
                            break;
                    }
                }
                else if (opr == RELOPR.GE)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new GeFloat();
                            break;
                        case TYPE.INT32:
                            i = new GeInt();
                            break;
                        case TYPE.INT64:
                            i = new GeLong();
                            break;
                    }
                }
                else if (opr == RELOPR.GT)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new GtFloat();
                            break;
                        case TYPE.INT32:
                            i = new GtInt();
                            break;
                        case TYPE.INT64:
                            i = new GtLong();
                            break;
                    }
                }
                else if (opr == RELOPR.LE)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new LeFloat();
                            break;
                        case TYPE.INT32:
                            i = new LeInt();
                            break;
                        case TYPE.INT64:
                            i = new LeLong();
                            break;
                    }
                }
                else if (opr == RELOPR.LT)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new LtFloat();
                            break;
                        case TYPE.INT32:
                            i = new LtInt();
                            break;
                        case TYPE.INT64:
                            i = new LtLong();
                            break;
                    }
                }
                else if (opr == RELOPR.NE)
                {
                    switch (LeftExpression.GetExprType())
                    {
                        case TYPE.FLOAT:
                            i = new NeFloat();
                            break;
                        case TYPE.INT32:
                            i = new NeInt();
                            break;
                        case TYPE.INT64:
                            i = new NeLong();
                            break;
                    }
                }
                CodeGenerationContext.Instructions[loc2] = i;
                SymbolTable.Deref = false;
                endLoc = loc2 + 1;
            }
            return endLoc;
        }

        public TYPE GetExprType()
        {
            if (Operator is BoolOpr)
            {
                return TYPE.BOOL;
            }
            else if (Operator is MultOpr)
            {
                return LeftExpression.GetExprType();
            }
            else if (Operator is AddOpr)
            {
                return LeftExpression.GetExprType();
            }
            else if (Operator is RelOpr)
            {
                return TYPE.BOOL;
            }
            else
            {
                throw new CheckerException(
                    "unexpected operator in dyadic expression -> imho, this exception should never be thrown :: " +
                    Operator);
            }
        }

        public bool IsLValue()
        {
            return false;
        }
    }
}
