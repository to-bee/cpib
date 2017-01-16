package vm;

import absSyn.IAbsSyn;
import conSyn.IConcSyn;
import context.Assignment;
import context.Context;
import context.TokenVm;
import context.VmVar;
import parser.Parser;
import scanner.Scanner;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.Ident;
import scanner.tokenList.ITokenList;
import virtualmachineFS2015.CodeArray;
import virtualmachineFS2015.ICodeArray;
import virtualmachineFS2015.IVirtualMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Malin on 16.01.2017.
 */
public class Compilation {
    private ICodeArray cArr = null;


    public Compilation() throws IVirtualMachine.ExecutionError {
        //Compiler.COMPILER.initializeVm(cArr,1000);
    }

    public Compilation(int codeArraySize, int storeSize) throws IVirtualMachine.ExecutionError {
        this.cArr = new CodeArray(codeArraySize);
        //this.generateCode();
        //Compiler.COMPILER.initializeVm(cArr,storeSize);
    }

    public void generateCode() throws IVirtualMachine.ExecutionError {
        IAbsSyn absSyn;

        String complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun addVar(param1:Compl) returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var bsp3:Int32;\n" +
                "var bsp4:Bool;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := (5+I*4)+(4+I*5);\n" +
                "bsp2 := 4-I*5;\n" +
                "bsp3 := 3;\n" +
                "bsp4 := true;\n" +
                "result := bsp1 + bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        Scanner scanner = new Scanner();
        ITokenList tokenList;
        tokenList = scanner.scan(complexAddProgram);
        System.out.println(tokenList.toString());

        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }


        try {
            Compiler.COMPILER.initialize();
            this.generateCode(Context.getVmVariablesMap());
        } catch (ICodeArray.CodeTooSmallError codeTooSmallError) {
            codeTooSmallError.printStackTrace();
        }

    }


    private void generateCode(Map<Ident, VmVar> assignments) throws ICodeArray.CodeTooSmallError {
        int loc = 0;
        for(Map.Entry<Ident, VmVar> a : assignments.entrySet()) {
            loc = VmInstructions.storageDeclaration(loc);
            a.getValue().setRelLocation(loc);
        }
        generateAssignmentCode(assignments, loc);

        Compiler.COMPILER.getCodeArray();
    }

    private int generateAssignmentCode(Map<Ident, VmVar> assignments, int loc) throws ICodeArray.CodeTooSmallError {
        for(Map.Entry<Ident, VmVar> a : assignments.entrySet()) {
            // switch bool, int, compl
            if (a.getValue().getAssignments().size() > 0 ) {
                for(Assignment aSub: a.getValue().getAssignments()) {
                    // falls Compl Abfrage ob components size 5 und erster Eintrag Literal
                    //if (aSub.components.
                    // falls Bool Abfrage ob components size 2 und erster Eintrag Ident
                    // falls Int ABfrage ob components size 2 und erster Eintrag Ident

                    // falls nicht zutreffend, rekursiv weiter anhand der Operatoren im zweiten Term
                    // bzw. weiter mit Variablen herausholen falls Ident nicht true oder false
                }

            }
        }
        return loc;
    }


    private IAbsSyn checkProgram(String addProgram) throws ContextError {
        ITokenList tokenList = null;
        try {
            Scanner scanner = new Scanner();
            tokenList = scanner.scan(addProgram);
//            scanner.printResult(addProgram, addProgram, tokenList);
        } catch (Exception lexicalError) {
            lexicalError.printStackTrace();
        }

        IConcSyn parseTree = null;
        try {
            parseTree = new Parser(tokenList);
            parseTree.parse();
        } catch (GrammarError grammarError) {
            grammarError.printStackTrace();
        }

        TokenVm vm = new TokenVm(tokenList);

        IAbsSyn absSyn = parseTree.toAbsSyn();
        absSyn.check();
        return absSyn;

    }

}
