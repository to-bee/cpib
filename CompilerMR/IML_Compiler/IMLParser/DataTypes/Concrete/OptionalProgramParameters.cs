using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalProgramParametersEps : IOptionalProgramParameters
    {
        public IAbsList<AbsProgramParameter> ToAbySyn()
        {
            return new AbsEmptyList<AbsProgramParameter>();
        }
    }

    class OptionalProgramParametersParams : IOptionalProgramParameters
    {
        public IOptionalFlowMode FlowMode { get; private set; }
        public IOptionalChangeMode ChangeMode { get; private set; }
        public ITypedIdent TypedIdent { get; private set; }
        public IRepeatingOptionalProgramParameters OptionalProgramParameters { get; private set; }

        public OptionalProgramParametersParams (IOptionalFlowMode flowMode, IOptionalChangeMode changeMode,
            ITypedIdent typedIdent, IRepeatingOptionalProgramParameters progParams)
        {
            FlowMode = flowMode;
            ChangeMode = changeMode;
            TypedIdent = typedIdent;
            OptionalProgramParameters = progParams;
        }

        public IAbsList<AbsProgramParameter> ToAbySyn()
        {
            var identInfo = TypedIdent.ToAbySyn();
            return new AbsProgramParameterList()
            {
                ProgramParameter =
                    new AbsProgramParameter()
                    {
                        ChangeMode = this.ChangeMode.ToAbySyn(),
                        FlowMode = this.FlowMode.ToAbsSyn(),
                        Ident = identInfo.Ident,
                        Type = identInfo.Type
                    },
                ProgramParameterList = OptionalProgramParameters.ToAbySyn()
            };
        }
    }
}
