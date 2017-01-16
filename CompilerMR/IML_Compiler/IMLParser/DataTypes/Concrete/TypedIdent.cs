using System.Net;
using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class TypedIdent : ITypedIdent
    {
        public Ident Ident { get; private set; }
        public Type Type { get; private set; }

        public TypedIdent(Ident ident, Type type)
        {
            Ident = ident;
            Type = type;
        }

        public AbsTypedIdent ToAbySyn()
        {
            return new AbsTypedIdent() {Ident = this.Ident.value, Type = this.Type.value};
        }
    }
}
