import absSyn.IAbsSyn;
import conSyn.IConcSyn;
import context.TokenVm;
import org.junit.Assert;
import org.junit.Test;
import parser.Parser;
import scanner.Scanner;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.errors.LexicalError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class TypeCheckTest {
    @Test
    public void testComplex() {
        IAbsSyn absSyn;
        String complexAddProgram;


        // possible
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testUnequals() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Int32;\n" +
                "var bsp2:Compl;\n" +
                "var isUnequals:Int32\n" +
                "do\n" +
                "bsp1 := 22;\n" +
                "bsp2 := 4-I*5;\n" +
                "isUnequals := abcd + bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
            Assert.fail();
        } catch (ContextError contextError) {
            System.out.println(contextError.getMessage());
        }



        // should fail because I is on the wrong place
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testEquals() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl\n" +
                "do\n" +
                "bsp1 := (5+4*I)+I*4\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError contextError) {
            System.out.println(contextError.getMessage());
        }

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

        // should fail because of floats are not allowed
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testFloat() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4.2;\n" +
                "result := bsp1 + bsp2 + 4-I*5 + boolVal\n" +
                "endfun\n" +
                "do\n" +
                "call testFloat()\n" +
                "endprogram";
        ITokenList tokenList = null;
        try {
            Scanner scanner = new Scanner();
            tokenList = scanner.scan(complexAddProgram);
            Assert.fail();
        } catch (LexicalError lexicalError) {
            System.out.println(lexicalError.getMessage());
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

    @Test
    public void testVariableTypes() {
        IAbsSyn absSyn;
        String variableTest;

        /*
         *  var a:int32     a:=2
         *  var a:bool      a:=true
         *  const a:bool    a:=true
         *  var a:int32     a:=true             LType and RType mismatch
         *  var a:bool      a:=2                LType and RType mismatch
         *  const a:bool    a:=true; a:=false   Variable a:Bool is const
         */
        variableTest = "program variableTest()\n" +
                "global\n" +
                "fun test01() returns result:int32\n" +
                "local\n" +
                "var a:bool\n" +
                "do\n" +
                "a := true;\n" +
                "a := false;\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call test01()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(variableTest);
            //Assert.fail();

        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testTupleTypes() {
        IAbsSyn absSyn;
        String tupleTest;

        /*
         *  const c:(bool,int32)    c:=(true,2)                 correct
         *  c:(bool,(bool,int32))   c := (true,(true,2))        accepted
         *  var c:(int32,int32)     c := (2,3)                  Tuple: c:(Int32, Int32) must be const
         *  const c:(bool)          c:=(true)                   GrammarError could not parse grammar
         *  c:(bool,int32,bool)     c := (true, 2)              Tuple: c:(Bool, Int32, Bool) cannot be assigned with: IDENT, LITERAL
         *  c:(bool,int32)          c:(true,2,2)                //TODO in TupleVar.java Tuple %s cannot take this many parameters
         *  c:(bool,int32)          c:(true,2); c:(false,1)     Tuple c:(Bool, Int32) is constant and can only be initialized once
         *  c:(bool,(bool,int32))   c := (true,(true,false))    LType and RType mismatch for variable: c:(Bool, Bool, Int32). Cannot assign: Int32 [TYPE] to IDENT, "false"
         *  d:(bool,(int32,int32))                              Tuple: d:(Bool, Int32, Int32) is never used
         */
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun addVar() returns d:(bool,(int32,int32))\n" +
                "local\n" +
                "e:(bool,(int32,int32));\n" +
                "var a:int32\n" +
                "do\n" +
                "a := 2;\n" +
                "e := (true,(2,2));\n" +
                "d := (true,(2,2))\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
            //Assert.fail();

        } catch (ContextError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testTuple2() {
        IAbsSyn absSyn;
        String tupleTest;

        // c has too many arguments assigned
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun addVar() returns result:int32\n" +
                "local\n" +
                "b:Compl;\n" +
                "c:(bool,int32)\n" +
                "do\n" +
                "b := 22;\n" +
                "c := (true, 2, 33);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(tupleTest);
            Assert.fail();

        } catch (
                ContextError e)

        {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testTuple3() {
        IAbsSyn absSyn;
        String tupleTest;
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
        try

        {
            absSyn = checkProgram(tupleTest);
        } catch (
                ContextError e)

        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testTuple4() {
        IAbsSyn absSyn;
        String tupleTest;

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
        try

        {
            absSyn = checkProgram(tupleTest);
            Assert.fail();
        } catch (
                ContextError e)

        {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }

    @Test
    public void testTuple5() {
        IAbsSyn absSyn;
        String tupleTest;
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
        try

        {
            absSyn = checkProgram(tupleTest);
            Assert.fail();
        } catch (
                ContextError e)

        {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }

    @Test
    public void testTuple6() {
        IAbsSyn absSyn;
        String tupleTest;
        // Should fail because of const multiple assignment
        tupleTest = "program TupleTest()\n" +
                "global\n" +
                "fun addVar() returns result:int32\n" +
                "local\n" +
                "c:(bool,int32)\n" +
                "do\n" +
                "c := (2, true);\n" +
                "c := (3, false);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try

        {
            absSyn = checkProgram(tupleTest);
            Assert.fail();
        } catch (
                ContextError e)

        {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }


}