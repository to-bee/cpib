package vm;

import absSyn.AbstractAbsSyn;
import absSyn.ExpressionAbsSyn;
import absSyn.IAbsSyn;
import context.Variable;
import virtualmachineFS2015.ICodeArray;
import virtualmachineFS2015.IInstructions;

import static vm.Compiler.COMPILER;

/**
 * Created by Malin on 15.01.2017.
 */
public class VmInstructions {


    //ADDOPR
    public static int addInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.AddInt());
        return loc + 1;
    }

    public static int addCompl(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.AddCompl());
        return loc + 1;
    }

    public static int subInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.SubInt());
        return loc + 1;
    }

    public static int subCompl(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.SubCompl());
        return loc + 1;
    }

    //MULTOPR
    public static int divInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.DivTruncInt());
        return loc + 1;
    }

    public static int multInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.MultInt());
        return loc + 1;
    }

    public static int multCompl(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.MultCompl());
        return loc + 1;
    }

    public static int modInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.ModTruncInt());
        return loc + 1;
    }

    public static int modCompl(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.ModTruncCompl());
        return loc + 1;
    }

    //Comparisons
    public static int eqInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.EqInt());
        return loc + 1;
    }

    public static int eqCompl(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.EqCompl());
        return loc + 1;
    }

    public static int geInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.GeInt());
        return loc + 1;
    }

    public static int gtInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.GtInt());
        return loc + 1;
    }

    public static int leInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.LeInt());
        return loc + 1;
    }

    public static int ltInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.LtInt());
        return loc + 1;
    }

    public static int neInt(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.NeInt());
        return loc + 1;
    }

    public static int neCompl(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc, new IInstructions.NeCompl());
        return loc + 1;
    }

    //&& ||
    public static int cAnd(int loc, IAbsSyn rExpr) throws ICodeArray.CodeTooSmallError  {
        int loc2 = loc + 1;
        int loc3 = rExpr.code(loc2);
        int loc4 = loc3 + 1;
        COMPILER.getCodeArray().put(loc3, new IInstructions.UncondJump(loc4 + 1));
        COMPILER.getCodeArray().put(loc, new IInstructions.CondJump(loc4));
        return loc4 + 1;
    }

    public static int cOr(int loc, IAbsSyn rExpr) throws ICodeArray.CodeTooSmallError  {
        int loc2 = loc + 1;
        int loc3 = loc2 + 2;
        int loc4 = rExpr.code(loc3);
        int loc5 = loc4 + 1;
        int loo6 = loc4 + 2;
        COMPILER.getCodeArray().put(loc, new IInstructions.AddInt());// 0 = FALSE / 1,2 = TRUE
        return loc + 1;
    }

    public static void storeGlobal(int loc, Variable var) throws ICodeArray.CodeTooSmallError  {
        COMPILER.getCodeArray().put(loc++, new IInstructions.LoadImInt(var.getAbsLocation()));
    }

    public static void storeLocal(int loc, Variable var) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc++, new IInstructions.LoadAddrRel(var.getRelLocation()));
    }

    public static void outputBool(int loc, ExpressionAbsSyn expr) throws ICodeArray.CodeTooSmallError  {
        COMPILER.getCodeArray().put(loc++, new IInstructions.OutputBool(expr.getToken().toString()));
    }

    public static void outputInt(int loc, ExpressionAbsSyn expr) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc++, new IInstructions.OutputInt(expr.getToken().toString()));
    }

    public static void outputCompl(int loc, ExpressionAbsSyn expr) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc++, new IInstructions.OutputCompl(expr.getToken().toString()));
    }

    public static void inputBool(int loc, IAbsSyn expr, String var) throws ICodeArray.CodeTooSmallError  {
        loc = expr.code(loc);
        COMPILER.getCodeArray().put(loc++, new IInstructions.InputBool(var));
    }

    public static void inputInt(int loc, IAbsSyn expr, String var) throws ICodeArray.CodeTooSmallError {
        loc = expr.code(loc);
        COMPILER.getCodeArray().put(loc++, new IInstructions.InputInt(var));
    }

    public static void inputCompl(int loc,  IAbsSyn expr, String var) throws ICodeArray.CodeTooSmallError {
        loc = expr.code(loc);
        COMPILER.getCodeArray().put(loc++, new IInstructions.InputCompl(var));
    }

    public static int assign(int loc, IAbsSyn exprLeft, AbstractAbsSyn exprRight) throws ICodeArray.CodeTooSmallError {
        loc = exprLeft.code(loc);
        loc = exprRight.code(loc);
        COMPILER.getCodeArray().put(loc++, new IInstructions.Store());
        return loc;
    }

    public static int storageDeclaration(int loc) throws ICodeArray.CodeTooSmallError {
        COMPILER.getCodeArray().put(loc++, new IInstructions.AllocBlock(1));
        return loc;
    }
}
