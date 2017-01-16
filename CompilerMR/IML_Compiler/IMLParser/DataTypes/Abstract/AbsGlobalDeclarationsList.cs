using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsGlobalDeclarationsList : IAbsList<IAbsDeclaration>
    {
        public IAbsDeclaration GlobalDeclaration { get; set; }
        public IAbsList<IAbsDeclaration> GlobalDeclarationsList { get; set; }
        public List<IAbsDeclaration> ToList()
        {
            var list = new List<IAbsDeclaration>();
            var currList = this;

            while (currList != null)
            {
                if (currList.GlobalDeclaration != null)
                    list.Add(currList.GlobalDeclaration);
                currList = currList.GlobalDeclarationsList as AbsGlobalDeclarationsList;
            }

            return list;
        }

        public int CodeRoutines(int loc)
        {
            var allDecls = ToList();
            var routineDecls = allDecls.Where(d => d is AbsFunctionDeclaration || d is AbsProcedureDeclaration).ToList();

            foreach (var d in routineDecls)
            {
                loc = d.Code(loc);
            }
            return loc;
        }

        public void Check()
        {
            var allDecls = ToList();
            var routineDecls = allDecls.Where(d => d is AbsFunctionDeclaration || d is AbsProcedureDeclaration).ToList();
            var varDecls = allDecls.Where(d => d is AbsStorageDeclaration).ToList();

            if (routineDecls.Count + varDecls.Count != allDecls.Count)
                throw new Exception("not all decls found");

            varDecls.ForEach(d => d.Check());
            routineDecls.ForEach(d => d.Check());
            routineDecls.ForEach(d => d.CheckAfterDeclaration());
        }

        public int Code(int loc)
        {
            var allDecls = ToList();
            var varDecls = allDecls.Where(d => d is AbsStorageDeclaration).ToList();
            var routineDecls = allDecls.Where(d => !(d is AbsStorageDeclaration));

            foreach (var d in varDecls)
            {
                loc = d.Code(loc);
            }

            foreach (var r in routineDecls)
            {
                if (r is AbsProcedureDeclaration)
                {
                    var p = (r as AbsProcedureDeclaration);
                    SymbolTable.ProcDecls.Add(p);
                }
                else
                {
                    var f = (r as AbsFunctionDeclaration);
                    SymbolTable.FuncDecls.Add(f);
                }
            }

            return loc;
        }
    }
}
