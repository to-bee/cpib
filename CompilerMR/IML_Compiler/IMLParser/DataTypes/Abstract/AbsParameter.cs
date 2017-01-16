using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsParameter : IAbsSyn
    {
        public FLOWMODE FlowMode { get; set; }
        public MECHMODE MechMode { get; set; }
        public AbsStorageDeclaration StorageDeclaration { get; set; }

        public void Check()
        {
            var ctx = (AbstractRoutineContext) GlobalContext.CurrentContext;

            ctx.AddParameter(this);


            // you are a nasty one ;)
            //GlobalContext.CurrentContext.GetVariable(StorageDeclaration.Ident).Initialized = true;
        }

        public int Code(int loc)
        {
            //NOTE: nothing needs to be done here, since all the parameters will be loaded when the routine starts
            return loc;
        }
    }
}
