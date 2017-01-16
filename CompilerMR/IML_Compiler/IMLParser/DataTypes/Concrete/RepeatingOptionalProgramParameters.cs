using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepeatingOptionalProgramParametersEps : IRepeatingOptionalProgramParameters
    {
        public IAbsList<AbsProgramParameter> ToAbySyn()
        {
            return new AbsEmptyList<AbsProgramParameter>();
        }
    }

    class RepeatingOptionalProgramParametersParams : IRepeatingOptionalProgramParameters
    {
        public IOptionalFlowMode FlowMode { get; private set; }
        public IOptionalChangeMode ChangeMode { get; private set; }
        public ITypedIdent TypedIdent { get; private set; }
        public IRepeatingOptionalProgramParameters RepeatingOptionalProgramParameters { get; private set; }

        public RepeatingOptionalProgramParametersParams(IOptionalFlowMode fmode, IOptionalChangeMode cmode,
            ITypedIdent tident, IRepeatingOptionalProgramParameters oparams)
        {
            FlowMode = fmode;
            ChangeMode = cmode;
            TypedIdent = tident;
            RepeatingOptionalProgramParameters = oparams;
        }

        public IAbsList<AbsProgramParameter> ToAbySyn()
        {
            var abs = TypedIdent.ToAbySyn();
            return new AbsProgramParameterList()
            {
                ProgramParameter =
                    new AbsProgramParameter()
                    {
                        ChangeMode = ChangeMode.ToAbySyn(),
                        FlowMode = FlowMode.ToAbsSyn(),
                        Ident = abs.Ident,
                        Type = abs.Type
                    },
                ProgramParameterList = RepeatingOptionalProgramParameters.ToAbySyn()
            };
        }
    }
}
