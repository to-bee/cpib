using IML_Compiler.IMLParser.DataTypes.Abstract;
using IML_Compiler.IMLScanner.DataTypes;

namespace IML_Compiler.IMLParser.DataTypes.Concrete
{
    class MonadicOperatorNot : IMonadicOperator
    {
        public MonadicOperator ToAbySyn()
        {
            return MonadicOperator.NOT;
        }
    }

    class MonadicOperatorAdd : IMonadicOperator
    {
        public AddOpr AddOpr { get; private set; }

        public MonadicOperatorAdd(AddOpr opr)
        {
            AddOpr = opr;
        }

        public MonadicOperator ToAbySyn()
        {
            return (AddOpr.value == ADDOPR.MINUS ? MonadicOperator.MINUS : MonadicOperator.PLUS);
        }
    }
}
