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
    public class AbsExpressionIdent : IAbsExpression
    {
        public string Ident { get; set; }
        public void Check()
        {
        }

        public int Code(int loc)
        {
            var v = SymbolTable.GetEntry(Ident);
            if (v.Scope == SymbolTable.Scope.Global && v.Access == SymbolTable.Access.Direct)
            {
                CodeGenerationContext.Instructions[loc] = new LoadImInt() {Value = v.Address};
                if (SymbolTable.Deref)
                {
                    CodeGenerationContext.Instructions[loc+1] = new Deref();
                    return loc + 2;
                }
                return loc + 1;
            }
            else if (v.Scope == SymbolTable.Scope.Local && v.Access == SymbolTable.Access.Direct)
            {
                CodeGenerationContext.Instructions[loc] = new LoadAddrRel() {RelAddress = v.Address};
                if (SymbolTable.Deref)
                {
                    CodeGenerationContext.Instructions[loc + 1] = new Deref();
                    return loc + 2;
                }
                return loc + 1;
            }
            else
            {
                CodeGenerationContext.Instructions[loc] = new LoadAddrRel() {RelAddress = v.Address};
                CodeGenerationContext.Instructions[loc + 1] = new Deref();
                if (SymbolTable.Deref)
                {
                    CodeGenerationContext.Instructions[loc + 2] = new Deref();
                    return loc + 3;
                }
                return loc + 2;
            } 
        }

        public TYPE GetExprType()
        {
            return GlobalContext.CurrentContext.GetVariable(Ident).Type;
        }

        public bool IsLValue()
        {
            return true;
        }
    }
}
