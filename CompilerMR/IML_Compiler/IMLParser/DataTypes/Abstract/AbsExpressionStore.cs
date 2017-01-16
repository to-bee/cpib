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
    public class AbsExpressionStore : IAbsExpression
    {
        public string Ident { get; set; }
        public void Check()
        {
            var v = GlobalContext.CurrentContext.GetVariable(Ident);
            if (v.Initialized)
            {
                //note: unfortunately doesn't really work with global imports....
                //throw new CheckerException($"variable {Ident} is already initialized");
            }

            v.Initialized = true;
        }

        public int Code(int loc)
        {
            // NOTE: in the slides there is a big section about initialization. we ignore this here...

            //var v = SymbolTable.GetEntry(Ident);

            //SymbolTable.SetAddress(v);

            return new AbsExpressionIdent() {Ident = Ident}.Code(loc);
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
