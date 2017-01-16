using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IML_Compiler
{
    public static class ArrayExtension
    {
        public static IEnumerable<T> SubSequence<T>(this T[] arr, int from, int to = -1)
        {
            if (to == -1)
                to = arr.Length;

            if (from < 0 || from > to)
                throw new ArgumentException();
            if (to < from || to > arr.Length)
                throw new ArgumentException();

            for (int i = from; i < to; i++)
            {
                yield return arr[i];
            }
        }
    }
}
