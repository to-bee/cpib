package vm;

import absSyn.IAbsSyn;
import scanner.Scanner;
import scanner.tokenList.ITokenList;
import virtualmachineFS2015.*;

/**
 * Created by Malin on 15.01.2017.
 */
public enum Compiler {
    COMPILER;

    private IVirtualMachine vm = null;
    private ICodeArray cArr = null;

    public void initializeVm(int codeArraySize, int storeSize) throws IVirtualMachine.ExecutionError {
        this.cArr = new CodeArray(codeArraySize);
        vm = new VirtualMachine(this.cArr, storeSize);
    }

    public void generateCode() throws IVirtualMachine.ExecutionError {
        initializeVm(1000, 1000);

        String complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun addVar(param1:Compl) returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := (5+I*4);\n" +
                "bsp2 := 4-I*5;\n" +
                "result := bsp1 + bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        Scanner scanner = new Scanner();
        ITokenList tokenList;
        tokenList = scanner.scan(complexAddProgram);
        System.out.println(tokenList.toString());

    }

    public void runCode() {

    }

    public IVirtualMachine getVM() {
        return this.vm;
    }

    public ICodeArray getCodeArray() {
        return cArr;
    }

}