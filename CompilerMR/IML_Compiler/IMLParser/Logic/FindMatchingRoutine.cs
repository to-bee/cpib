using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IML_Compiler.IMLParser.DataTypes;
using IML_Compiler.IMLParser.DataTypes.Abstract;

namespace IML_Compiler.IMLParser.Logic
{
    public class FindMatchingRoutine
    {
        public static AbstractRoutineContext ExistMatchingRoutine(AbsCmdCall procCall)
        {
            return GetMatchingRoutine<ProcContext>(procCall.Ident, procCall.ExpressionList.ToList());
        }

        public static AbstractRoutineContext ExistMatchingRoutine(AbsExpressionFuncCall funcCal)
        {
            return GetMatchingRoutine<FunctionContext>(funcCal.Ident, funcCal.Parameter.ToList());
        }

        private static AbstractRoutineContext GetMatchingRoutine<T>(string routineName, List<IAbsExpression> args)
            where T : AbstractRoutineContext
        {

            var routines = GlobalContext.Context.GetRoutines<T>(routineName);
            if (routines.Count == 0) 
                throw new CheckerException($"No routine with the specified type (function, procedure) named {routineName} found");

            var matchingRoutines = new List<T>();

            foreach (var routine in routines)
            {
                if (routine.Parameters.Count != args.Count)
                    continue;

                bool match = true;

                for (int i = 0; i < args.Count; i++)
                {
                    if (routine.Parameters[i].Type != args[i].GetExprType())
                    {
                        match = false;
                        break;
                    }
                }
                if (match)
                    matchingRoutines.Add(routine);
            }

            if (matchingRoutines.Count == 0)
            {
                throw new CheckerException($"No matching signature found for routine {routineName}");
            }

            if (matchingRoutines.Count > 1)
            {
                throw new CheckerException($"More than one matching routine found for {routineName}");
            }

            return matchingRoutines[0];
        }
    }
}
