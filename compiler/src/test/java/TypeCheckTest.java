import absSyn.IAbsSyn;
import conSyn.IConcSyn;
import context.TokenVm;
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
public class TypeCheckTest {
    @Test
    public void testComplex() {
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

        String complexAddProgram;

        // should fail because of boolVal
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun addVar() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var boolVal:Bool;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "boolVal := true;\n" +
                "result := bsp1 + bsp2 + 4-I*5 + boolVal\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
            Assert.fail();
        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }

        /**
         * Check ! allowed for boolean (true | false)
         */
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testComplementBool() returns s:Int32\n" +
                "local\n" +
                "var bsp2NoCompl:Bool;\n" +
                "var complementVar:Bool\n" +
                "do\n" +
                "bsp2NoCompl := true;\n" +
                "complementVar := !bsp2NoCompl\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError e) {
            e.printStackTrace();
            Assert.fail();
        }

        // possible
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testEquals() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var isEquals:Bool\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "isEquals := bsp1 == bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError contextError) {
            contextError.printStackTrace();
            Assert.fail();
        }

        // possible
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testUnequals() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var isUnequals:Bool\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "isUnequals := bsp1 != bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError contextError) {
            contextError.printStackTrace();
            Assert.fail();
        }

        /**
         * Check ! (not allowed for complex)
         */
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun getComplementComplex() returns s:Int32\n" +
                "local\n" +
                "var bsp1Compl:Compl;\n" +
                "var complementVar:Bool\n" +
                "do\n" +
                "bsp1Compl := (5+I*4)+I*4;\n" +
                "complementVar := !bsp1Compl\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
            Assert.fail();
        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }

        /**
         * Check &&, || (not allowed for complex)
         */
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testGt() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var andOpVar:Bool\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "andOpVar := bsp1 && bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
            Assert.fail();
        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }

        /**
         * All rel operations but ==/!= are not allowed
         */
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testGt() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var isEquals:Bool\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "isEquals := bsp1 >= bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
            Assert.fail();
        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }

        // Normal addVar
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun addVar() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "result := bsp1 + bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError contextError) {
            contextError.printStackTrace();
            Assert.fail();
        }
    }

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

        // c has less arguments assigned
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun addVar() returns result:int32\n" +
                "local\n" +
                "c:(bool,int32, bool)\n" +
                "do\n" +
                "c := (true, 2);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
            Assert.fail();

        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }

        // c has too many arguments assigned
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun addVar() returns result:int32\n" +
                "local\n" +
                "c:(bool,int32)\n" +
                "do\n" +
                "c := (true, 2, 33);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
            Assert.fail();

        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }


        // Thats ok
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun addVar() returns result:int32\n" +
                "local\n" +
                "c:(bool,int32)\n" +
                "do\n" +
                "c := (true, 2);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
        } catch (ContextError e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }

        // Should fail because of const multiple assignment
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun addVar() returns result:int32\n" +
                "local\n" +
                "c:(bool,int32)\n" +
                "do\n" +
                "c := (2, true);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
            Assert.fail();
        } catch (ContextError e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }

        // Should fail because of const multiple assignment
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun addVar() returns result:int32\n" +
                "local\n" +
                "const a:int32\n" +
                "do\n" +
                "a := false;\n" +
                "a := true;\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
            Assert.fail();
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

        TokenVm vm = new TokenVm(tokenList);

        IAbsSyn absSyn = parseTree.toAbsSyn();
        absSyn.check();
        return absSyn;

    }


}