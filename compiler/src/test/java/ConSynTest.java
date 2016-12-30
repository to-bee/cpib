import conSyn.IConcSyn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import scanner.Scanner;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class ConSynTest {

    @Before
    public void init() {

    }


    @Test
    public void testParser() {




    }

    @Test
    public void testComplex() {
        IConcSyn parseTree;
        String complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun add(arg1:Compl) returns s:Int32\n" +
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
                "call add()\n" +
                "endprogram";
        parseTree = checkProgram(complexAddProgram);
        System.out.println(parseTree.toString());

        String complexMultiplyProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun add(arg1:Compl) returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := 5+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "result := bsp1 * bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        parseTree = checkProgram(complexMultiplyProgram);
    }

    @Test
    public void tupleAdvanced() {
        String program = "program AdvancedTuple()\n" +
                "global\n" +
                  "fun test(testdata:(int32,int32)) returns testrun001:(bool,(int32,int32))\n" +
                  "local\n" +
                    "const testrun:(bool,(int32,int32));\n" +
                    "var a:int32;\n" +
                    "var b:int32\n" +
                  "do\n" +
                    "a := mytuple1[0];\n" +
                    "b := mytuple1[1];\n" +
                    "testrun001 := (b >= 5,(a,b))\n" +
                  "endfun\n" +
                "do\n" +
                  "call test((1200,6))\n" +
                "endprogram";
        IConcSyn parseTree2 = checkProgram(program);
        parseTree2.toString();
    }

    @Test
    public void testTuple() {
        String program = "program TupleTest()\n" +
                "global\n" +
                "fun add(mytuple1:(int32,int32)) returns s:Int32\n" +
                "local\n" +
                "const mytuple1:(int32,int32)\n" +
                "do\n" +
                "mytuple1 := (1,2);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        IConcSyn parseTree2 = checkProgram(program);
        parseTree2.toString();
    }


    private IConcSyn checkProgram(String addProgram) {
        ITokenList tokenList = null;
        try {
            Scanner scanner = new Scanner();
            tokenList = scanner.scan(addProgram);
//            scanner.printResult(addProgram, addProgram, tokenList);
        } catch (Exception lexicalError) {
            lexicalError.printStackTrace();
        }

        try {
            Parser parseTree = new Parser(tokenList);
            parseTree.parse();
            return parseTree;
        } catch (GrammarError grammarError) {
            grammarError.printStackTrace();
            Assert.fail();
        }

        return null;
    }


}