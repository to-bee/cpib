using System.Xml.Schema;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.DataTypes.Concrete;
using IML_Compiler.IMLParser.Logic;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsFunctionDeclaration : IAbsDeclaration
    {
        public string Ident { get; set; }
        public IAbsList<AbsParameter> ParameterList { get; set; }
        public AbsStorageDeclaration ReturnType { get; set; }
        public IAbsList<AbsGlobalImport> GlobalImports { get; set; }
        public IAbsList<AbsStorageDeclaration> LocalStorageDeclarations { get; set; }
        public IAbsList<IAbsCmd> BlockCmd { get; set; }

        private FunctionContext myCheckContext;

        public void Check()
        {
            var fCtx = new FunctionContext(Ident, ReturnType.TypeDeclaration);
            var oldCtx = GlobalContext.CurrentContext;
            GlobalContext.CurrentContext = fCtx;

            ParameterList.Check();
            ReturnType.Check();
            GlobalImports.Check();
            LocalStorageDeclarations.Check();
            
            GlobalContext.Context.AddRoutineContext(fCtx);
            GlobalContext.CurrentContext = oldCtx;

            myCheckContext = fCtx;
        }

        public int Code(int loc)
        {
            SymbolTable.CurrentScope = SymbolTable.Scope.Local;

            var oldCtx = GlobalContext.CurrentContext;
            GlobalContext.CurrentContext = myCheckContext;

            int relAddr;
            int returnSize;

            loc = SymbolTable.LoadRoutineParametersIntoContext(loc, Ident, ParameterList.ToList(), out returnSize,
                out relAddr);

            var e = new SymbolTable.Entry()
            {
                Address = relAddr,
                Access = SymbolTable.Access.Direct,
                Name = ReturnType.Ident,
                Scope = SymbolTable.Scope.Local,
                Type = ReturnType.TypeDeclaration
            };
            SymbolTable.Table.Add(e);

            loc = GlobalImports.Code(loc);
            loc = LocalStorageDeclarations.Code(loc);
            loc = BlockCmd.Code(loc);

            CodeGenerationContext.Instructions[loc] = new Return() {Size = returnSize};
            loc++;

            GlobalContext.CurrentContext = oldCtx;

            SymbolTable.ClearLocals();
            SymbolTable.CurrentScope = SymbolTable.Scope.Global;

            return loc;
        }

        public void CheckAfterDeclaration()
        {
            var oldCtx = GlobalContext.CurrentContext;
            GlobalContext.CurrentContext = myCheckContext;

            BlockCmd.Check();

            GlobalContext.CurrentContext = oldCtx;
        }
    }
}
