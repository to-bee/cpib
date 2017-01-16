using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.Logic;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsIdentList : IAbsList<string>
    {
        public string Ident { get; set; }
        public IAbsList<string> IdentList { get; set; }
        public List<string> ToList()
        {
            var list = new List<string>();
            var currList = this;

            while (currList != null)
            {
                if (currList.Ident != null)
                    list.Add(currList.Ident);
                currList = currList.IdentList as AbsIdentList;
            }

            return list;
        }

        public void Check()
        {
            if (Ident != null) GlobalContext.CurrentContext.GetVariable(Ident);
            IdentList.Check();
        }

        public int Code(int loc)
        {
            //NOTE: since this is only a list of identifiers and this is probably used for multiple purposes, process this at higher level
            throw new NotSupportedException("Implement this at a higher abstraction level");
        }
    }
}
