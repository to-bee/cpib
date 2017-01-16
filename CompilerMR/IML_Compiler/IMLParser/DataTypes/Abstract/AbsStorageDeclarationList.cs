using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.Logic;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsStorageDeclarationList : IAbsList<AbsStorageDeclaration>
    {
        public AbsStorageDeclaration StorageDeclaration { get; set; }
        public IAbsList<AbsStorageDeclaration> StorageDeclarationList { get; set; }
        public List<AbsStorageDeclaration> ToList()
        {
            var list = new List<AbsStorageDeclaration>();
            var currList = this;

            while (currList != null)
            {
                if (currList.StorageDeclaration != null)
                    list.Add(currList.StorageDeclaration);
                currList = currList.StorageDeclarationList as AbsStorageDeclarationList;
            }

            return list;
        }

        public void Check()
        {
            StorageDeclaration?.Check();
            StorageDeclarationList.Check();
        }

        public int Code(int loc)
        {
            var list = ToList();
            foreach (var d in list)
            {
                loc = d.Code(loc);
            }

            return loc;
        }
    }
}
