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
    public class AbsGlobalImport : IAbsSyn
    {
        public CHANGEMODE ChangeMode { get; set; }
        public FLOWMODE FlowMode { get; set; }
        public string Ident { get; set; }
        public void Check()
        {
            var routineCtx = (AbstractRoutineContext) GlobalContext.CurrentContext;
            routineCtx.AddGlobalImport(Ident, ChangeMode == CHANGEMODE.VAR);
        }

        public int Code(int loc)
        {
            if (FlowMode == FLOWMODE.IN)
            {
                var e = new SymbolTable.Entry()
                {
                    Access = SymbolTable.Access.Direct,
                    Name = Ident,
                    Type = SymbolTable.GetEntry(Ident).Type
                };

                var glE = SymbolTable.GetEntry(Ident);
                CodeGenerationContext.Instructions[loc] = new LoadImInt() {Value = glE.Address};
                loc++;
                CodeGenerationContext.Instructions[loc] = new Deref();
                loc++;

                SymbolTable.AddEntry(e);
                SymbolTable.SetAddress(e);
            }

            return loc;
        }
    }
}
