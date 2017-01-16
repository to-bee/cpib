using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class ProgramParameterList : IProgramParameterList
    {
        public IOptionalProgramParameters OptionalProgramParameters { get; private set; }

        public ProgramParameterList (IOptionalProgramParameters progparams)
        {
            OptionalProgramParameters = progparams;
        }

        public IAbsList<AbsProgramParameter> ToAbySyn()
        {
            return new AbsProgramParameterList() {ProgramParameterList = OptionalProgramParameters.ToAbySyn()};
        }
    }
}
