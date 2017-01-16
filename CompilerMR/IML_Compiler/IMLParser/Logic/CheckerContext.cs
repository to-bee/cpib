using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.Logic
{
    [Obsolete("u never should have used that", true)]
    public class CheckerContext
    {
        private static CheckerContext context = null;

        public static CheckerContext Context
        {
            get
            {
                if (context == null)
                {
                    context = new CheckerContext();
                }
                return context;
            }
        }


        public struct VarProperties
        {
            public string Name { get; set; }
            public bool Initialized { get; set; }
            public TYPE Type { get; set; }
            public bool Writable { get; set; }
        }

        public IDictionary<string, VarProperties> GlobalContext { get; set; }
        public IDictionary<string, VarProperties> CurrentContext { get; set; }

        public CheckerContext()
        {
            GlobalContext = new Dictionary<string, VarProperties>();
        }

        public VarProperties GetVar(string name)
        {
            if (CurrentContext != null && CurrentContext.ContainsKey(name))
                return CurrentContext[name];
            if (GlobalContext.ContainsKey(name))
                return GlobalContext[name];

            throw new CheckerException($"{name} is not defined");
        }

        private void AddVarToCurrentContext(string name, TYPE type, bool initialized = false, bool writable = false)
        {
            if (GlobalContext.ContainsKey(name))
                throw new CheckerException($"{name} already in global context defined");

            if (CurrentContext == null) 
                throw new CheckerException($"declaration of {name} not allowed here");

            if (CurrentContext.ContainsKey(name))
                throw new CheckerException($"{name} already defined in current context");

            CurrentContext.Add(name, new VarProperties() {Initialized = initialized, Type = type, Name = name, Writable = writable});
        }

        private void AddVarToGlobalContext(string name, TYPE type, bool initialized = false, bool writable = false)
        {
            if (GlobalContext.ContainsKey(name))
                throw new CheckerException($"{name} already in global context defined");

            GlobalContext.Add(name, new VarProperties() {Initialized = initialized, Type = type, Name = name, Writable = writable});
        }

        public void AddVarToContext(string name, TYPE type, bool initialized = false, bool writable = false)
        {
            if (CurrentContext == null)
            {
                AddVarToGlobalContext(name, type, initialized, writable);
            }
            else
            {
                AddVarToCurrentContext(name, type, initialized, writable);
            }
        }

        public void NewCurrentContext()
        {
            if (CurrentContext != null)
                throw new CheckerException("Cannot define a new current context inside a non global context");

            CurrentContext = new Dictionary<string, VarProperties>();
        }

        public void CloseCurrentContext()
        {
            if (CurrentContext == null) 
                throw new CheckerException("No current context open which can be closed");

            CurrentContext = null;
        }
    }
}
