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
public class ParserTest {

    @Before
    public void init() {

    }


    @Test
    public void testParser() {
        String complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun add(bsp1:Compl) returns s:Int32\n" +
                    "local\n" +
                    "var bsp1:Compl;\n" +
                    "var bsp2:Compl;\n" +
                    "var result:Compl\n" +
                    "do\n" +
                        "bsp1 := 5+I*4;\n" +
                        "bsp2 := 4-I*5;\n" +
                        "result := bsp1 + bsp2\n" +
                "endfun\n" +
                "do\n" +
                    "call add()\n" +
                "endprogram";
        checkProgram(complexAddProgram);


        /**
         * program
         *      ComplexTest
         *          ()
         */


        String complexMultiplyProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun add(bsp1:Compl) returns s:Int32\n" +
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
        checkProgram(complexMultiplyProgram);
    }

    private void checkProgram(String addProgram) {
        ITokenList tokenList = null;
        try {
            Scanner scanner = new Scanner();
            tokenList = scanner.scan(addProgram);
            scanner.printResult(addProgram, addProgram, tokenList);
        } catch (Exception lexicalError) {
            lexicalError.printStackTrace();
        }

        try {
            Parser parseTree = new Parser(tokenList);
            parseTree.parse();
            System.out.println("done");
        } catch (GrammarError grammarError) {
            grammarError.printStackTrace();
            Assert.fail();
        }
    }


}