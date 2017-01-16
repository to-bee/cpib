using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class OptionalParametersEps : IOptionalParameters
    {
        public IAbsList<AbsParameter> ToAbySyn()
        {
            return new AbsEmptyList<AbsParameter>();
        }
    }

    class OptionalParametersParams : IOptionalParameters
    {
        public IParameter Parameter { get; private set; }
        public IRepeatingOptionalParameters RepeatingOptionalParameters { get; private set; }

        public OptionalParametersParams(IParameter param, IRepeatingOptionalParameters rparams) 
        {
            Parameter = param;
            RepeatingOptionalParameters = rparams;
        }

        public IAbsList<AbsParameter> ToAbySyn()
        {
            return new AbsParameterList()
            {
                Parameter = Parameter.ToAbySyn(),
                ParameterList = RepeatingOptionalParameters.ToAbySyn()
            };
        }
    }
}
