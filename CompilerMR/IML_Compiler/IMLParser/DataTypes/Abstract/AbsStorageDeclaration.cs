using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsStorageDeclaration : IAbsDeclaration
    {
        public CHANGEMODE ChangeMode { get; set; }
        public string Ident { get; set; }
        public IMLScanner.DataTypes.TYPE TypeDeclaration { get; set; }

        public void Check()
        {
            GlobalContext.CurrentContext.AddVariable(Ident, TypeDeclaration, ChangeMode == CHANGEMODE.VAR);
        }

        public int Code(int loc)
        {
            var e = new SymbolTable.Entry() { Access = SymbolTable.Access.Direct, Name = Ident, Type = TypeDeclaration };
            SymbolTable.AddEntry(e);
            SymbolTable.SetAddress(e);
            CodeGenerationContext.Instructions[loc] = new AllocBlock() {Size = 1};
            return loc+1;
        }

        public void CheckAfterDeclaration()
        {
            // nothing to do here
        }
    }
}
