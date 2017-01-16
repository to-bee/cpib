using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsGlobalImportList : IAbsList<AbsGlobalImport>
    {
        public AbsGlobalImport GlobalImport { get; set; }
        public IAbsList<AbsGlobalImport> GlobalImports { get; set; }
        public List<AbsGlobalImport> ToList()
        {
            var list = new List<AbsGlobalImport>();
            var currList = this;

            while (currList != null)
            {
                if (currList.GlobalImport != null)
                    list.Add(currList.GlobalImport);
                currList = currList.GlobalImports as AbsGlobalImportList;
            }

            return list;
        }

        public void Check()
        {
            GlobalImport?.Check();
            GlobalImports.Check();
        }

        public int Code(int loc)
        {
            foreach (var i in ToList())
            {
                loc = i.Code(loc);
            }
            return loc;
        }
    }
}
