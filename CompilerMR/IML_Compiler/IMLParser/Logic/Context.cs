using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using IML_Compiler.IMLParser.DataTypes;
using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;
using IML_Compiler.IMLScanner.Logic;
using Type = System.Type;

namespace IML_Compiler.IMLParser.Logic
{
    public abstract class Context
    {
        public abstract VarProperties GetVariable(string name);
        public abstract void AddVariable(string name, TYPE type, bool writable = false);
    }

    public class GlobalContext : Context // List of global vars, funcs, procs
    {
        private static GlobalContext ctx;

        public static GlobalContext Context
        {
            get
            {
                if (ctx == null)
                    ctx = new GlobalContext();
                return ctx;
            }
        }

        public static Context CurrentContext { get; set; }

        public List<AbstractRoutineContext> RoutineContexts { get; set; }
        public List<VarProperties> Variables { get; set; }

        public override VarProperties GetVariable(string name)
        {
            var vv = Variables.FirstOrDefault(v => v.Name == name);
            if (vv == null) throw new CheckerException($"Variable named {name} not found in scope");
            return vv;
        }

        public List<T> GetRoutines<T>(string name) where T : AbstractRoutineContext
        {
            var res = RoutineContexts.Where(r => r.Name == name && r is T).Select(f => (T)f).ToList();
            if (res.Count == 0)
                throw new CheckerException($"no {(typeof(T) == typeof(FunctionContext) ? "function" : "procedure")} called {name} found");

            return res;
        }

        public override void AddVariable(string name, TYPE type, bool writable = false)
        {
            if (Variables.Any(v => v.Name == name))
                throw new CheckerException($"A variable with the name {name} already exists in the global context");

            Variables.Add(new VarProperties() { Address = -1, Initialized = false, Name = name, Type = type, Writable = writable});
        }

        public void AddRoutineContext(AbstractRoutineContext routine)
        {
            var sameNameRoutines = RoutineContexts.Where(r => r.Name == routine.Name).ToList();

            if (sameNameRoutines.Count > 0)
            {
                if (sameNameRoutines[0].GetType() != routine.GetType())
                {
                    if (routine is FunctionContext)
                    {
                        throw new CheckerException($"Function {routine.Name} can't have the same name as a procedure");
                    }
                    else
                    {
                        throw new CheckerException($"Procedure {routine.Name} can't have the same name as a function");
                    }
                }

                if (routine is FunctionContext)
                {
                    if (((FunctionContext)routine).ReturnType != ((FunctionContext)sameNameRoutines[0]).ReturnType)
                        throw new CheckerException($"Function {routine.Name} must have same return type as other functions with name {routine.Name}");
                }

                if (sameNameRoutines.Any(r => CompareParams(r, routine)))
                    throw new CheckerException($"There already exists a routine with the name {routine.Name} with the same signature");
            }

            RoutineContexts.Add(routine);
        }

        private bool CompareParams(AbstractRoutineContext r, AbstractRoutineContext routine)
        {
            if (r.Parameters.Count != routine.Parameters.Count)
                return false;
            for (int i = 0; i < r.Parameters.Count; i++)
            {
                if (r.Parameters[i].Type !=
                    routine.Parameters[i].Type)
                    return false;
            }
            return true;
        }

        private GlobalContext()
        {
            RoutineContexts = new List<AbstractRoutineContext>();
            Variables = new List<VarProperties>();
            CurrentContext = this;
        }
    }

    public class VarProperties
    {
        private bool initialized = false;

        public string Name { get; set; }

        public bool Initialized
        {
            get
            {
                return initialized;
            }
            set
            {
                if (initialized)
                {
                    // note: bad things happen here with global imports...
                    //throw new CheckerException($"{Name} is already initialized");
                }

                initialized = value;
            }
        }
        public TYPE Type { get; set; }
        public bool Writable { get; set; }
        public int Address { get; set; }
    }

    public abstract class AbstractRoutineContext : Context
    {
        public string Name { get; set; }
        public List<VarProperties> Parameters { get; set; }
        public List<VarProperties> ImportedGlobalVars { get; set; }
        public List<VarProperties> LocalVars { get; set; }

        public override VarProperties GetVariable(string name)
        {
            var v = ImportedGlobalVars.FirstOrDefault(va => va.Name == name);
            if (v != null) return v;

            v = Parameters.FirstOrDefault(p => p.Name == name);
            if (v != null) return v;

            v = LocalVars.FirstOrDefault(vv => vv.Name == name);
            if (v == null) throw new CheckerException($"Variable {name} not found in current scope");
            return v;
        }

        public void AddParameter(AbsParameter param)
        {
            if (Parameters.Any(p => p.Name == param.StorageDeclaration.Ident))
                throw new CheckerException($"Parameter with name {param.StorageDeclaration.Ident} already exists in {Name}");

            Parameters.Add(new VarProperties()
            {
                Address = -1,
                Initialized = param.FlowMode == FLOWMODE.IN || param.FlowMode == FLOWMODE.INOUT || param.MechMode == MECHMODE.REF,
                Name = param.StorageDeclaration.Ident,
                Writable = true,
                Type = param.StorageDeclaration.TypeDeclaration
            });
        }

        public void AddGlobalImport(string name, bool writable)
        {
            if (Parameters.Any(p => p.Name == name))
                throw new CheckerException($"{name} already exists as parameter in {Name}");

            var vp = GlobalContext.Context.Variables.FirstOrDefault(v => v.Name == name);
            if (vp == null)
                throw new CheckerException($"{name} doesn't exist in the global namespace");

            if (ImportedGlobalVars.Any(v => v.Name == name))
                throw new ScannerException($"Globalimport with name {name} already exists in {Name}");

            vp.Writable = writable;
            //vp.Initialized = true;
            ImportedGlobalVars.Add(vp);
        }

        public override void AddVariable(string name, TYPE type, bool writable = false)
        {
            AddLocalVar(name, type, writable);
        }

        private void AddLocalVar(string name, TYPE type, bool writable = false)
        {
            if (Parameters.Any(p => p.Name == name))
                throw new CheckerException($"{name} already exists as parameter in {Name}");

            if (ImportedGlobalVars.Any(v => v.Name == name))
                throw new CheckerException($"{name} already exists as global import in {Name}");

            if (LocalVars.Any(v => v.Name == name))
                throw new CheckerException($"{name} already defined in {Name}");

            LocalVars.Add(new VarProperties() { Address = -1, Initialized = false, Name = name, Type = type, Writable = writable });
        }

        protected AbstractRoutineContext(string name)
        {
            Name = name;
            Parameters = new List<VarProperties>();
            ImportedGlobalVars = new List<VarProperties>();
            LocalVars = new List<VarProperties>();
        }
    }

    public class FunctionContext : AbstractRoutineContext // List of params, return value, globalimports, localstorage
    {
        public TYPE ReturnType { get; set; }


        public FunctionContext(string name, TYPE returnType)
            : base(name)
        {
            ReturnType = returnType;
        }
    }

    public class ProcContext : AbstractRoutineContext // List of params
    {
        public ProcContext(string name) : base(name)
        {
        }
    }
}
