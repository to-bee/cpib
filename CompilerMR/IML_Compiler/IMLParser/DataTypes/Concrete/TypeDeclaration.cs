using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class _deprecated_TypeDeclarationType //: ITypeDeclaration
    {
        public IMLScanner.DataTypes.Type Type { get; private set; }

        public void TypeDeclarationType(IMLScanner.DataTypes.Type type)
        {
            Type = type;
        }
    }

    class _deprecated_TypeDeclarationIdent //: TypeDeclaration
    {
        public Ident Ident { get; private set; }

        public void TypeDeclarationIdent(Ident ident)
        {
            Ident = ident;
        }
    }
}
