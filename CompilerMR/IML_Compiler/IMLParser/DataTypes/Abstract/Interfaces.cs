using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public interface IAbsSyn
    {
        void Check();
        int Code(int loc);
    }

    /// <summary>
    /// Do not implement this interface, unless you know *exactly* what you are doing, use the generic version <seealso cref="IAbsList{T}"/> of this Interface!
    /// </summary>
    public interface IAbsList : IAbsSyn
    {
    }

    public interface IAbsList<T> : IAbsList
    {
        List<T> ToList();
    }

    public interface IAbsCmd : IAbsSyn
    {
        
    }

    public interface IAbsDeclaration : IAbsSyn
    {
        void CheckAfterDeclaration();
    }

    public interface IAbsExpression : IAbsSyn
    {
        TYPE GetExprType();
        bool IsLValue();
    }
}
