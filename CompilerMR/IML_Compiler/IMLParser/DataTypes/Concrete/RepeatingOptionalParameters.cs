using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class RepeatingOptionalParametersEps : IRepeatingOptionalParameters
    {
        public IAbsList<AbsParameter> ToAbySyn()
        {
            return new AbsEmptyList<AbsParameter>();
        }
    }

    class RepeatingOptionalParametersParams : IRepeatingOptionalParameters
    {
        public IParameter Parameter { get; private set; }
        public IRepeatingOptionalParameters RepeatingOptionalParameters { get; private set; }

        public RepeatingOptionalParametersParams (IParameter param, IRepeatingOptionalParameters oparams)
        {
            Parameter = param;
            RepeatingOptionalParameters = oparams;
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
