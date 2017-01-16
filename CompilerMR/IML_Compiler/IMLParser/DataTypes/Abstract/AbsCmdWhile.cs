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
    public class AbsCmdWhile : IAbsCmd
    {
        public IAbsExpression Condition { get; set; }
        public IAbsList<IAbsCmd> BlockCmd { get; set; }
        public void Check()
        {
            Condition.Check();
            BlockCmd.Check();

            if (Condition.GetExprType() != TYPE.BOOL) 
                throw new CheckerException("Condition expression of while condition must be a bool expression");
        }

        public int Code(int loc)
        {
            SymbolTable.Deref = true;
            var loc1 = Condition.Code(loc);
            SymbolTable.Deref = false;
            var loc2 = loc1 + 1;
            var loc3 = BlockCmd.Code(loc2);
            var loc4 = loc3 + 1;
            CodeGenerationContext.Instructions[loc3] = new UncondJump() {Address = loc};
            CodeGenerationContext.Instructions[loc1] = new CondJump() {Address = loc4};
            return loc4;
        }
    }
}
