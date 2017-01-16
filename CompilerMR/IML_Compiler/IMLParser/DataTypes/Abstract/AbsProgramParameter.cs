using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes.CodeCreation;
using IML_Compiler.IMLParser.Logic;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Abstract
{
    public class AbsProgramParameter : IAbsSyn
    {
        public FLOWMODE FlowMode { get; set; }
        public CHANGEMODE ChangeMode { get; set; }
        public string Ident { get; set; }
        public TYPE Type { get; set; }

        public AbsProgramParameter() { }

        public void Check()
        {
            GlobalContext.Context.AddVariable(Ident, Type, ChangeMode == CHANGEMODE.VAR);
        }

        public int Code(int loc)
        {
            throw new NotSupportedException("Use methods CodeIn(int loc) or CodeOut(int loc) instead");
        }

        public int CodeIn(int loc)
        {
            var e = new SymbolTable.Entry()
            {
                Access = SymbolTable.Access.Direct,
                Name = Ident,
                Type = Type
            };
            SymbolTable.AddEntry(e);
            SymbolTable.SetAddress(e);

            if (FlowMode == FLOWMODE.IN || FlowMode == FLOWMODE.INOUT)
            {
                IInstruction instr = null;
                switch (Type)
                {
                    case TYPE.BOOL:
                        instr = new ProgParameterBool();
                        break;
                    case TYPE.FLOAT:
                        instr = new ProgParameterFloat();
                        break;
                    case TYPE.INT32:
                        instr = new ProgParameterInt();
                        break;
                    case TYPE.INT64:
                        instr = new ProgParameterLong();
                        break;
                }

                CodeGenerationContext.Instructions[loc] = instr;
                loc++;
            }
            else
            {
                CodeGenerationContext.Instructions[loc] = new AllocBlock() {Size = 1};
                loc++;
            }

            return loc;
        }

        public int CodeOut(int loc)
        {
            if (FlowMode == FLOWMODE.INOUT || FlowMode == FLOWMODE.OUT)
            {
                var e = SymbolTable.GetEntry(Ident);
                CodeGenerationContext.Instructions[loc] = new LoadImInt() {Value = e.Address};
                loc++;
                CodeGenerationContext.Instructions[loc] = new Deref();
                loc++;

                IInstruction instr = null;
                switch (Type)
                {
                    case TYPE.BOOL:
                        instr = new OutputBool() {Indicator = Ident};
                        break;
                    case TYPE.FLOAT:
                        instr = new OutputFloat() {Indicator = Ident};
                        break;
                    case TYPE.INT32:
                        instr = new OutputInt() {Indicator = Ident};
                        break;
                    case TYPE.INT64:
                        instr = new OutputLong() {Indicator = Ident};
                        break;
                }

                CodeGenerationContext.Instructions[loc] = instr;
                loc++;
            }
            return loc;
        }
    }
}
