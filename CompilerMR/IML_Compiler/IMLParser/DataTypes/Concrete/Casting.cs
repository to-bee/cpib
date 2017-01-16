using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class Casting : ICasting
    {
        public IML_Compiler.IMLScanner.DataTypes.Type Type { get; private set; }

        public Casting(IMLScanner.DataTypes.Type type)
        {
            Type = type;
        }


        public TYPE ToAbySyn()
        {
            return Type.value;
        }
    }
}
