using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class ParameterList : IParameterList
    {
        public IOptionalParameters OptionalParameters { get; private set; }

        public ParameterList (IOptionalParameters oparams)
        {
            OptionalParameters = oparams;
        }

        public IAbsList<AbsParameter> ToAbySyn()
        {
            return new AbsParameterList() {ParameterList = OptionalParameters.ToAbySyn()};
        }
    }
}
