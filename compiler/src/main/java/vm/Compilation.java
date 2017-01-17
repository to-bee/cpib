package vm;

import absSyn.IAbsSyn;
import com.sun.org.apache.xpath.internal.operations.Variable;
import conSyn.IConcSyn;
import context.*;
import parser.Parser;
import scanner.Scanner;
import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.token.BaseToken;
import scanner.token.Ident;
import scanner.token.Literal;
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

    public void generateCode(String complexAddProgram) throws IVirtualMachine.ExecutionError, ContextError {
        IAbsSyn absSyn;

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

        ICodeArray cArr = Compiler.COMPILER.getCodeArray();
        System.out.println(cArr.toString());
    }


    private void generateCode(Map<Ident, VmVar> assignments) throws ICodeArray.CodeTooSmallError, ContextError {
        int loc = 0;
        int i = 0;
        for(Map.Entry<Ident, VmVar> a : assignments.entrySet()) {
            Var var = Var.getVariables().get(i);
            if (var.getClass() == DefaultVar.class) {
                loc = VmInstructions.storageDeclaration(loc);
                a.getValue().setRelLocation(loc);
            } else if (var.getClass() == TupleVar.class) {
                TupleVar tupVar = (TupleVar) var;
                loc = VmInstructions.storageDeclaration(loc, tupVar.getLeftSideTokens().size());
                a.getValue().setRelLocation(loc);
            }
            i++;
        }
        generateAssignmentCode(assignments, loc);

        Compiler.COMPILER.getCodeArray();
    }

    private int generateAssignmentCode(Map<Ident, VmVar> assignments, int loc) throws ICodeArray.CodeTooSmallError, ContextError {
        for(Map.Entry<Ident, VmVar> a : assignments.entrySet()) {
            // switch bool, int, compl
            if (a.getValue().getAssignments().size() > 0 ) {
                for(int i = 0; i < a.getValue().getAssignments().size(); i++) {
                    Assignment aSub = a.getValue().getAssignments().get(i);
                    Var var = Var.getVariables().get(i);
                    DefaultVar defVar = null;
                    TupleVar tupVar = null;
                    if (var.getClass() == DefaultVar.class) {
                        defVar = (DefaultVar) var;
                        if (aSub.getRValueType() == Terminal.COMPL) {
                            if (aSub.getComponents().size() == 6 && aSub.getComponents().get(0).getClass() == Literal.class) {
                                VmInstructions.storeLocal(loc, a.getValue().getRelLocation());
                                loc++;
                                if (((BaseToken)aSub.getComponents().get(1)).getTerminal() == Terminal.MINOPR) {
                                    VmInstructions.loadImCompl(loc, (int)((Literal)aSub.getComponents().get(0)).getValue(), 0 - (int)((Literal)aSub.getComponents().get(4)).getValue());
                                    loc++;
                                } else {
                                    VmInstructions.loadImCompl(loc, (int)((Literal)aSub.getComponents().get(0)).getValue(), (int)((Literal)aSub.getComponents().get(4)).getValue());
                                    loc++;
                                }
                            }

                            if (aSub.getComponents().size() == 7 && aSub.getComponents().get(1).getClass() == Literal.class) {
                                VmInstructions.storeLocal(loc, a.getValue().getRelLocation());
                                loc++;
                                int real = 0;
                                if (((BaseToken)aSub.getComponents().get(1)).getTerminal() == Terminal.MINOPR) {
                                    real -= (int) ((Literal) aSub.getComponents().get(1)).getValue();
                                } else {
                                    real += (int) ((Literal) aSub.getComponents().get(1)).getValue();
                                }
                                if (((BaseToken)aSub.getComponents().get(2)).getTerminal() == Terminal.MINOPR) {
                                    VmInstructions.loadImCompl(loc, real, 0 - (int)((Literal)aSub.getComponents().get(4)).getValue());
                                    loc++;
                                } else {
                                    VmInstructions.loadImCompl(loc, real, (int)((Literal)aSub.getComponents().get(4)).getValue());
                                    loc++;
                                }
                            }
                        }
                        if (aSub.getRValueType() == Terminal.INT32) {
                            if (aSub.getComponents().size() == 2 && aSub.getComponents().get(0).getClass() == Literal.class) {
                                VmInstructions.storeLocal(loc, a.getValue().getRelLocation());
                                loc++;
                                VmInstructions.loadImInt(loc, (int)((Literal)aSub.getComponents().get(0)).getValue());
                                loc++;
                            }
                            if (aSub.getComponents().size() == 3 && aSub.getComponents().get(1).getClass() == Literal.class) {
                                VmInstructions.storeLocal(loc, a.getValue().getRelLocation());
                                loc++;
                                if (((BaseToken)aSub.getComponents().get(1)).getTerminal() == Terminal.MINOPR) {
                                    VmInstructions.loadImInt(loc, 0- (int) ((Literal) aSub.getComponents().get(1)).getValue());
                                    loc++;
                                } else {
                                    VmInstructions.loadImInt(loc, (int) ((Literal) aSub.getComponents().get(1)).getValue());
                                    loc++;
                                }
                            }
                        }
                        if (aSub.getRValueType() == Terminal.BOOL) {
                            if (aSub.getComponents().size() == 2 && aSub.getComponents().get(0).getClass() == Ident.class) {
                                VmInstructions.storeLocal(loc, a.getValue().getRelLocation());
                                loc++;
                                if (((Ident)aSub.getComponents().get(0)).getValue() == "true") {
                                    VmInstructions.loadImInt(loc, 1);
                                    loc++;
                                } else {
                                    VmInstructions.loadImInt(loc, 0);
                                    loc++;
                                }
                            }
                        }
                    } else if (var.getClass() == TupleVar.class) {
                        tupVar = (TupleVar) var;
                        // TODO
                    }

                    // TODO
                    // falls nicht zutreffend, rekursiv weiter anhand der Operatoren im zweiten Term
                    // switch add, min, mult, div, mod
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
