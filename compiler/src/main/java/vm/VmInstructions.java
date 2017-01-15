package vm;

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

}