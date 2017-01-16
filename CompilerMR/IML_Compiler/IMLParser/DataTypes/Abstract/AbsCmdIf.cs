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
    public class AbsCmdIf : IAbsCmd
    {
        public IAbsExpression Condition { get; set; }
        public IAbsList<IAbsCmd> BlockCmd { get; set; }
        public IAbsList<IAbsCmd> ElseBlockCmd { get; set; }
        public void Check()
        {
            Condition.Check();
            BlockCmd.Check();
            ElseBlockCmd.Check();

            if (Condition.GetExprType() != TYPE.BOOL)
                throw new CheckerException("Condition Expression for if must be an expression of type bool");
        }

        public int Code(int loc)
        {
            SymbolTable.Deref = true;
            var loc1 = Condition.Code(loc);
            SymbolTable.Deref = false;

            var ifLoc = loc1 + 1;
            var afterIfBlock = BlockCmd.Code(ifLoc);

            var elseBlock = afterIfBlock + 1;

            var endBlock = ElseBlockCmd.Code(elseBlock);

            CodeGenerationContext.Instructions[loc1] = new CondJump() {Address = elseBlock};
            CodeGenerationContext.Instructions[afterIfBlock] = new UncondJump() {Address = endBlock};

            return endBlock;
        }
    }
}
