using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.Logic;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsProgram : IAbsSyn
    {
        public string Ident { get; set; }
        public IAbsList<AbsProgramParameter> ProgramParameterList { get; set; }
        public IAbsList<IAbsDeclaration> GlobalDeclaractions { get; set; }
        public IAbsList<IAbsCmd> BlockCmd { get; set; }
        public void Check()
        {
            // ident doesn't need to be checked
            ProgramParameterList.Check();
            GlobalDeclaractions.Check();
            BlockCmd.Check();
        }

        public int Code(int loc)
        {
            var ProgParams = ProgramParameterList as AbsProgramParameterList;

            var loc1 = ProgParams.CodeIn(loc);
            var loc2 = GlobalDeclaractions.Code(loc1);
            var loc3 = BlockCmd.Code(loc2);
            var loc4 = ProgParams.CodeOut(loc3);
            var loc5 = loc4 + 1;
            CodeGenerationContext.Instructions[loc4] = new Stop();
            var loc6 = (GlobalDeclaractions as AbsGlobalDeclarationsList).CodeRoutines(loc5);
            
            SymbolTable.FixRoutineJumps();

            return loc5;
        }
    }
}
