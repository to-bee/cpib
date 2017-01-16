using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsEmptyList<T> : IAbsList<T>
    {
        public List<T> ToList()
        {
            return new List<T>();
        }

        public void Check()
        {
            //empty list, no checks need to be made, this list is good to go ;)
        }

        public int Code(int loc)
        {
            return loc;
        }
    }
}
