using System;
using System.CodeDom;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsExpressionLiteral : IAbsExpression
    {
        public Literal Literal { get; set; }
        public void Check()
        {
            // only a value, ntohing to check
        }

        public int Code(int loc)
        {
            switch (Literal.literalType)
            {
                case TYPE.BOOL:
                    CodeGenerationContext.Instructions[loc] = new LoadImInt() { Value = ((bool)Literal.value) ? 1 : 0 };
                    break;
                case TYPE.FLOAT:
                    CodeGenerationContext.Instructions[loc] = new LoadImFloat() { Value = ((float)Literal.value) };
                    break;
                case TYPE.INT32:
                    CodeGenerationContext.Instructions[loc] = new LoadImInt() { Value = ((int)Literal.value) };
                    break;
                case TYPE.INT64:
                    CodeGenerationContext.Instructions[loc] = new LoadImLong() { Value = ((long)Literal.value) };
                    break;
            }
            return loc + 1;
        }

        public TYPE GetExprType()
        {
            return Literal.literalType;
        }

        public bool IsLValue()
        {
            return false;
        }
    }
}
