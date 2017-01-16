import absSyn.IAbsSyn;
import conSyn.IConcSyn;
import org.junit.Assert;
import org.junit.Test;
import parser.Parser;
import scanner.Scanner;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class TupleTypeCheckTest {
    @Test
    public void testTuple() {
        IAbsSyn absSyn;
        /**
         * scope checking
         * global storage delcaration, local storage declaration not the same name
         * fun parameter not the same name as local storage declarations
         * local storage declarations not two variables with the same name
         *
         * type checking
         * string not assigneable to int etc.
         *
         * typechecker
         * DONE: <=, >=, contexterror,
         * DONE: ==, != funktioniert
         * DONE: division, evt. modulo contexterror
         * DONE: COMPLEMENT contexterror, TODO: complement only allowed for bool?
         * DONE: &&, || contexterror
         *
         * Init checks
         *
         *
         */

        String program;


        String tupleTest;

        // Should fail because of tuple type declaration is wrong
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun add() returns result:int32\n" +
                "local\n" +
                "const a:int32\n" +
                "do\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
        } catch (ContextError e) {
            e.printStackTrace();
            Assert.fail();
        }

        // Should fail because of const multiple assignment
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun add() returns result:int32\n" +
                "local\n" +
                "var a:bool;\n" +
                "var b:int32\n" +
                "const c:(bool,int32);\n" +
                "do\n" +
                "a := false;\n" +
                "a := true;\n" +
                "b := 2;\n" +
                "b := 2;\n" +
                "c := (true,2);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
//            Assert.fail();
        } catch (ContextError e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
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
            Assert.fail();
        }

        IAbsSyn absSyn = parseTree.toAbsSyn();
        absSyn.check();
        return absSyn;

    }


}